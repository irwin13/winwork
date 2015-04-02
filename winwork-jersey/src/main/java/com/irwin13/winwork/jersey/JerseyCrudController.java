package com.irwin13.winwork.jersey;

import com.google.common.base.Strings;
import com.irwin13.winwork.core.SkipField;
import com.irwin13.winwork.core.WinWorkConstants;
import com.irwin13.winwork.core.WinWorkUtil;
import com.irwin13.winwork.core.dao.EntityResolver;
import com.irwin13.winwork.core.model.*;
import com.irwin13.winwork.core.service.WinWorkService;
import com.irwin13.winwork.servlet.WinWorkSession;
import com.irwin13.winwork.velocity.VelocityConfiguration;
import com.irwin13.winwork.velocity.VelocityWrapper;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by irwin on 4/2/15.
 */
public abstract class JerseyCrudController<M extends WinWorkEntity, I extends Serializable> {

    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final String COMMON_PAGE_PACKAGE = "common/";
    public static final String ERROR_PAGE_PACKAGE = "error/";

    public static final String GENERIC_LIST_PAGE= "list_page.html";

    public static final String LIST_PAGE_SUFFIX = "_list.html";
    public static final String LIST_AJAX_PAGE_SUFFIX = "_listAjax.html";

    public static final String DETAIL_PAGE_SUFFIX = "_detail.html";
    public static final String CREATE_PAGE_SUFFIX = "_create.html";
    public static final String EDIT_PAGE_SUFFIX = "_edit.html";

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final VelocityWrapper velocityWrapper = new VelocityWrapper();
    private final WinWorkSession winWorkSession = new WinWorkSession();
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    protected abstract WinWorkService<M, I> service();
    protected abstract String modelName();
    protected abstract String vmRootFolder();
    protected abstract VelocityConfiguration velocityConfiguration();
    protected abstract EntityResolver entityResolver();

    protected void setReferenceData(Map<String, Object> objectMap) {}
    protected void readAdditionalParameter(MultivaluedMap<String, String> formMap, Object model) {}

    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_HTML)
    public String listPage(@Context HttpServletRequest request) {

        List<KeyValue> keyValueList = sortableField();
        Map<String, Object> objectMap = winWorkSession.mapWithLoginUser(request);
        objectMap.put("modelName", modelName());
        objectMap.put("propertyList", keyValueList);

        String html = velocityWrapper.generateWebPage(vmRootFolder() + GENERIC_LIST_PAGE,
                velocityConfiguration(), objectMap);

        return html;
    }

    @GET
    @Path("/listAjax")
    @Produces(MediaType.TEXT_HTML)
    public String ajaxListPage(@Context HttpServletRequest request) {

        String searchKeyword = readParameterSearchKeyword(request);
        SortParameter sortParameter = readParameterSort(request);
        int pageStart = readParameterPageStart(request);
        int pageSize = readParameterPageSize(request);

        List<? extends WinWorkEntity> list = service().selectSearch(new SearchParameter(
                searchKeyword, sortParameter.getColumnName(), sortParameter.getSortMethod()),
                pageStart, pageSize);
        long total = service().selectSearchCount(searchKeyword);

        PagingModel pagingModel = WinWorkUtil.generatePagingModel(total, pageStart, pageSize);

        Map<String, Object> objectMap = winWorkSession.mapWithLoginUser(request);
        objectMap.put("list", list);
        objectMap.put("pagingModel", pagingModel);
        objectMap.put("pageSize", pageSize);
        objectMap.put("modelName", modelName());

        String html = velocityWrapper.generateWebPage(vmRootFolder() + modelName() + LIST_AJAX_PAGE_SUFFIX,
                velocityConfiguration(), objectMap);

        return html;
    }

    @GET
    @Path("/detail")
    @Produces(MediaType.TEXT_HTML)
    public String detailPage(@Context HttpServletRequest request,
                             @QueryParam("id") I id) {
        logger.debug("id = {}", id);
        WinWorkEntity model = service().getById(id, true);

        Map<String, Object> objectMap = winWorkSession.mapWithLoginUser(request);
        objectMap.put("model", model);
        objectMap.put("modelName", modelName());
        String html = velocityWrapper.generateWebPage(vmRootFolder() + modelName() + DETAIL_PAGE_SUFFIX,
                velocityConfiguration(), objectMap);

        return html;
    }

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    public String createPage(@Context HttpServletRequest request) {
        Map<String, Object> objectMap = winWorkSession.mapWithLoginUser(request);
        objectMap.put("modelName", modelName());
        setReferenceData(objectMap);
        String html = velocityWrapper.generateWebPage(vmRootFolder() + modelName() + CREATE_PAGE_SUFFIX,
                velocityConfiguration(), objectMap);

        return html;
    }

    @GET
    @Path("/edit")
    @Produces(MediaType.TEXT_HTML)
    public String editPage(@Context HttpServletRequest request, @QueryParam("id") I id) {
        logger.debug("id = {}", id);
        WinWorkEntity model = service().getById(id, true);

        Map<String, Object> objectMap = winWorkSession.mapWithLoginUser(request);
        objectMap.put("model", model);
        objectMap.put("modelName", modelName());
        setReferenceData(objectMap);
        String html = velocityWrapper.generateWebPage(vmRootFolder() + modelName() + EDIT_PAGE_SUFFIX,
                velocityConfiguration(), objectMap);

        return html;
    }

    @GET
    @Path("/delete")
    @Produces(MediaType.TEXT_HTML)
    public Response delete(@Context HttpServletRequest request, @QueryParam("id") I id) throws URISyntaxException {
        logger.debug("id = {}", id);
        M model = service().getById(id, true);
        if (model != null) {
            winWorkSession.setUserLogged(request, model, true);
            service().softDelete(model);
        }
        return redirectListPage(modelName());
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Object create(@Context HttpServletRequest request,
                           MultivaluedMap<String, String> formMap)
            throws InstantiationException, IllegalAccessException, URISyntaxException {

        logger.debug("formMap = {}", formMap);
        M model = service().getModelClass().newInstance();
        genericReadFormParameter(model, formMap);
        readAdditionalParameter(formMap, model);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<M>> constraintViolations = validator.validate(model);

        if (constraintViolations.size() == 0) {
            winWorkSession.setUserLogged(request, model, false);
            service().insert(model);
            return redirectListPage(modelName());
        } else {
            Map<String, Object> objectMap = winWorkSession.mapWithLoginUser(request);
            objectMap.put("modelName", modelName());
            objectMap.put("model", model);
            objectMap.put("errorMessage", constraintViolations);
            setReferenceData(objectMap);
            String html = velocityWrapper.generateWebPage(vmRootFolder() + modelName() + CREATE_PAGE_SUFFIX,
                    velocityConfiguration(), objectMap);
            return html;
        }
    }

    @POST
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Object edit(@Context HttpServletRequest request, MultivaluedMap<String, String> formMap) throws
            URISyntaxException {

        logger.debug("formMap = {}", formMap);
        I id = (I) formMap.getFirst("id");

        M model = service().getById(id, true);
        genericReadFormParameter(model, formMap);
        readAdditionalParameter(formMap, model);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<M>> constraintViolations = validator.validate(model);

        if (constraintViolations.size() == 0) {
            winWorkSession.setUserLogged(request, model, true);
            service().update(model);
            return redirectListPage(modelName());
        } else {
            Map<String, Object> objectMap = winWorkSession.mapWithLoginUser(request);
            objectMap.put("modelName", modelName());
            objectMap.put("model", model);
            objectMap.put("errorMessage", constraintViolations);
            setReferenceData(objectMap);
            String html = velocityWrapper.generateWebPage(vmRootFolder() + modelName() + EDIT_PAGE_SUFFIX,
                    velocityConfiguration(), objectMap);
            return html;
        }
    }

    private List<KeyValue> sortableField() {
        List<KeyValue> result = new LinkedList<KeyValue>();
        Map<String, Class<?>> sortableMap = service().sortableFields();
        for (Map.Entry<String, Class<?>> entry : sortableMap.entrySet()) {
            String display = entry.getKey();
            if (display.contains(".")) {
                display = display.substring(display.lastIndexOf(".") + 1, display.length());
            }
            KeyValue keyValue = new KeyValue(entry.getKey(), WinWorkUtil.insertStringInCamelCase(true, display, " "));
            result.add(keyValue);
        }
        return result;
    }

    protected void genericReadFormParameter(Object model, MultivaluedMap formMap) {
        if (model != null) {
            Field[] fieldArray = model.getClass().getDeclaredFields();
            for (Field field : fieldArray) {
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();

                boolean readField = true;

                Annotation[] annotationArray = field.getAnnotations();
                annotationScan:
                for (int a = 0; a < annotationArray.length; a++) {
                    Annotation annotation = annotationArray[a];
                    if (annotation.annotationType().equals(SkipField.class)) {
                        readField = false;
                        break annotationScan;
                    }
                }

                if (readField) {
                    String paramValue = (String) formMap.getFirst(fieldName);
                    try {
                        if (fieldType.equals(String.class)) {
                            PropertyUtils.setProperty(model, fieldName, paramValue);
                            logger.debug("set field {} with value = {}", fieldName, paramValue);
                        } else if (fieldType.equals(Integer.class)) {
                            Integer value = null;
                            if (!Strings.isNullOrEmpty(paramValue)) {
                                value = Integer.valueOf(paramValue);
                            }
                            PropertyUtils.setProperty(model, fieldName, value);
                            logger.debug("set field {} with value = {}", fieldName, paramValue);
                        } else if (fieldType.equals(Long.class)) {
                            Long value = null;
                            if (!Strings.isNullOrEmpty(paramValue)) {
                                value = Long.valueOf(paramValue);
                            }
                            PropertyUtils.setProperty(model, fieldName, value);
                            logger.debug("set field {} with value = {}", fieldName, paramValue);
                        } else if (fieldType.equals(Float.class)) {
                            Float value = null;
                            if (!Strings.isNullOrEmpty(paramValue)) {
                                value = Float.valueOf(paramValue);
                            }
                            PropertyUtils.setProperty(model, fieldName, value);
                            logger.debug("set field {} with value = {}", fieldName, paramValue);
                        } else if (fieldType.equals(Double.class)) {
                            Double value = null;
                            if (!Strings.isNullOrEmpty(paramValue)) {
                                value = Double.valueOf(paramValue);
                            }
                            PropertyUtils.setProperty(model, fieldName, value);
                            logger.debug("set field {} with value = {}", fieldName, paramValue);
                        } else if (fieldType.equals(Date.class)) {
                            if (!Strings.isNullOrEmpty(paramValue)) {
                                Date date = WinWorkConstants.SDF_DEFAULT.parse(paramValue);
                                if (date != null) {
                                    PropertyUtils.setProperty(model, fieldName, date);
                                    logger.debug("set field {} with value = {}", fieldName, date);
                                }
                            } else {
                                PropertyUtils.setProperty(model, fieldName, null);
                            }
                        } else if (fieldType.equals(Boolean.class)) {
                            if (!Strings.isNullOrEmpty(paramValue)) {
                                PropertyUtils.setProperty(model, fieldName, Boolean.TRUE);
                                logger.debug("set field {} with value = {}", fieldName, Boolean.TRUE);
                            } else {
                                PropertyUtils.setProperty(model, fieldName, Boolean.FALSE);
                                logger.debug("set field {} with value = {}", fieldName, Boolean.FALSE);
                            }
                        } else if (WinWorkEntity.class.isAssignableFrom(fieldType)) {
                            if (!Strings.isNullOrEmpty(paramValue)) {
                                logger.debug("set field {} with type {} using id = {}",
                                        new Object[]{fieldName, fieldType, paramValue});
                                Object bean = entityResolver().getById(paramValue, fieldType);
                                if (model != null) {
                                    PropertyUtils.setProperty(model, fieldName, bean);
                                    logger.debug("set field {} with type {} success", fieldName, fieldType);
                                }
                            } else {
                                PropertyUtils.setProperty(model, fieldName, null);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        logger.error(e.getLocalizedMessage(), e);
                    } catch (InvocationTargetException e) {
                        logger.error(e.getLocalizedMessage(), e);
                    } catch (NoSuchMethodException e) {
                        logger.error(e.getLocalizedMessage(), e);
                    } catch (ParseException e) {
                        logger.error(e.getLocalizedMessage(), e);
                    } catch (NumberFormatException e) {
                        logger.error(e.getLocalizedMessage(), e);
                    }
                }
            }
        } else {
            throw new NullPointerException("model is null, please fix your code");
        }
    }

    protected Response redirectResponse(String uri) throws URISyntaxException {
        return Response.temporaryRedirect(new URI(uri)).status(Response.Status.SEE_OTHER).build();
    }

    protected Response redirectListPage(String modelName) throws URISyntaxException {
        String webContext = velocityConfiguration().getContextRootUrl();
        logger.debug("contextRootUrl to redirect = {}", webContext);
        return redirectResponse(webContext + "/" + modelName + "/list");
    }

    protected SortParameter readParameterSort(HttpServletRequest request) {
        String sortMethod = request.getParameter("sortMethod");
        String sortProperty = request.getParameter("sortProperty");
        if (Strings.isNullOrEmpty(sortProperty)) sortProperty = WinWorkConstants.DEFAULT_PROPERTY_ORDER;

        return new SortParameter(sortProperty, setOrderMethod(sortMethod));
    }

    protected String readParameterSearchKeyword(HttpServletRequest request) {
        String search = request.getParameter("searchKeyword");
        search = WinWorkUtil.setSearchKeyword(search);
        return search;
    }

    protected int readParameterPageStart(HttpServletRequest request) {
        int result = 0;
        String param = request.getParameter("pageStart");
        try {
            result = Integer.valueOf(param);
        } catch (NumberFormatException e) {}
        return result;
    }

    protected int readParameterPageSize(HttpServletRequest request) {
        int result = DEFAULT_PAGE_SIZE;
        String param = request.getParameter("pageSize");
        try {
            result = Integer.valueOf(param);
        } catch (NumberFormatException e) {}
        return result;
    }

    private String setOrderMethod(String input) {
        if (Strings.isNullOrEmpty(input)) {
            return SortParameter.DESC;
        } else {
            if (input.equalsIgnoreCase(SortParameter.ASC)) {
                return SortParameter.ASC;
            } else {
                return SortParameter.DESC;
            }
        }
    }

}
