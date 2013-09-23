package id.co.quadras.winwork.controller;

import com.google.inject.Inject;
import id.co.quadras.winwork.model.entity.BaseEntity;
import id.co.quadras.winwork.model.vo.KeyValue;
import id.co.quadras.winwork.model.vo.PagingModel;
import id.co.quadras.winwork.model.vo.SortParameter;
import id.co.quadras.winwork.service.BasicOperationService;
import id.co.quadras.winwork.shared.WebPage;
import id.co.quadras.winwork.util.PagingUtil;
import id.co.quadras.winwork.validator.AbstractValidator;
import id.co.quadras.winwork.validator.ValidationStatus;
import id.co.quadras.winwork.validator.ValidatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * @author irwin Timestamp : 23/04/13 18:03
 *
 *
 */
public abstract class CrudController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudController.class);

    public static final String SELECT = "/select";
    public static final String SELECT_PAGED = "/selectPaged";
    public static final String SELECT_COUNT = "/selectCount";

    public static final String SELECT_SEARCH = "/selectSearch";
    public static final String SELECT_SEARCH_PAGED = "/selectSearchPaged";
    public static final String SELECT_SEARCH_COUNT = "/selectSearchCount";

    public static final String INSERT = "/insert";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String SOFT_DELETE = "/softDelete";
    public static final String INSERT_OR_UPDATE = "/insertOrUpdate";

    @Inject
    protected WebPage webPage;

    protected abstract void setReferenceData(Map<String, Object> objectMap);
    protected abstract void readAdditionalParameter(MultivaluedMap<String, String> formMap, Object model);

    public Response basicListPage(HttpServletRequest request, Class<?> modelClass, String modelName) {
        List<KeyValue> keyValueList = webPage.sortableField(modelClass);
        Map<String, Object> objectMap = webPage.mapWithLoginUser(request);
        objectMap.put("modelName", modelName);
        objectMap.put("propertyList", keyValueList);
        String content = webPage.stringFromVm(WebPage.COMMON_PAGE_PACKAGE + WebPage.GENERIC_LIST_PAGE, objectMap);
        return webPage.okResponse(content);
    }

    public Response basicListAjaxPage(HttpServletRequest request, BasicOperationService service, String modelName,
                                      String packageName) {

        String searchKeyword = webPage.readParameterSearchKeyword(request);
        SortParameter sortParameter = webPage.readParameterSort(request);
        int pageStart = webPage.readParameterPageStart(request);
        int pageSize = webPage.readParameterPageSize(request);

        List<? extends BaseEntity> list = service.selectSearchPaged(searchKeyword, sortParameter, pageStart, pageSize);
        long total = service.selectSearchCount(searchKeyword);

        PagingModel pagingModel = PagingUtil.getPagingModel(total, pageStart, pageSize);

        Map<String, Object> objectMap = webPage.mapWithLoginUser(request);
        objectMap.put("list", list);
        objectMap.put("pagingModel", pagingModel);
        objectMap.put("pageSize", pageSize);
        objectMap.put("modelName", modelName);

        String content = webPage.stringFromVm(packageName + modelName + WebPage.LIST_AJAX_PAGE_SUFFIX, objectMap);

        return webPage.okResponse(content);

    }

    public Response basicDetailPage(HttpServletRequest request, BasicOperationService service,
                                    String modelName, String packageName, String id) {

        BaseEntity model = service.getById(id, true);

        Map<String, Object> objectMap = webPage.mapWithLoginUser(request);
        objectMap.put("model", model);
        objectMap.put("modelName", modelName);
        String content = webPage.stringFromVm(packageName + modelName + WebPage.DETAIL_PAGE_SUFFIX, objectMap);

        return webPage.okResponse(content);
    }

    public Response basicCreatePage(HttpServletRequest request, String modelName, String packageName) {
        Map<String, Object> objectMap = webPage.mapWithLoginUser(request);
        objectMap.put("modelName", modelName);
        setReferenceData(objectMap);
        String content = webPage.stringFromVm(packageName + modelName + WebPage.CREATE_PAGE_SUFFIX, objectMap);
        return webPage.okResponse(content);
    }

    public Response basicEditPage(HttpServletRequest request, BasicOperationService service,
                                  String modelName, String packageName, String id) {

        BaseEntity model = service.getById(id, true);

        Map<String, Object> objectMap = webPage.mapWithLoginUser(request);
        objectMap.put("model", model);
        objectMap.put("modelName", modelName);
        setReferenceData(objectMap);
        String content = webPage.stringFromVm(packageName + modelName + WebPage.EDIT_PAGE_SUFFIX, objectMap);

        return webPage.okResponse(content);
    }

    public Response basicDelete(HttpServletRequest request, BasicOperationService service, String modelName, String id) throws URISyntaxException {
        BaseEntity model = service.getById(id, true);
        if (model != null) {
            webPage.setUserLogged(request, model, true);
            service.softDelete(model);
        }
        return webPage.redirectListPage(modelName);
    }

    public Response basicCreate(HttpServletRequest request, BasicOperationService service,
                                Class<? extends BaseEntity> modelClass,
                                String modelName, String packageName,
                                MultivaluedMap<String, String> formMap, AbstractValidator validator)
            throws InstantiationException, IllegalAccessException, URISyntaxException {

        LOGGER.debug("formMap = {}", formMap);
        BaseEntity model = modelClass.newInstance();
        webPage.genericReadFormParameter(model, formMap);
        readAdditionalParameter(formMap, model);
        ValidatorResult<? extends BaseEntity> validatorResult = validator.onCreate(model, webPage.displayLang(request));

        if (validatorResult.getValidationStatus().equals(ValidationStatus.SUCCESS)) {
            webPage.setUserLogged(request, model, false);
            service.insert(model);
            return webPage.redirectListPage(modelName);
        } else {
            Map<String, Object> objectMap = webPage.mapWithLoginUser(request);
            objectMap.put("modelName", modelName);
            objectMap.put("model", model);
            objectMap.put("errorMessage", validatorResult.getFieldMessages());
            setReferenceData(objectMap);
            String content = webPage.stringFromVm(packageName + modelName + WebPage.CREATE_PAGE_SUFFIX, objectMap);
            return webPage.okResponse(content);
        }
    }

    public Response basicEdit(HttpServletRequest request, BasicOperationService service,
                              String modelName, String packageName,
                              MultivaluedMap<String, String> formMap, AbstractValidator validator)
            throws URISyntaxException {

        LOGGER.debug("formMap = {}", formMap);
        String id = formMap.getFirst("id");

        BaseEntity model = service.getById(id, true);
        webPage.genericReadFormParameter(model, formMap);
        readAdditionalParameter(formMap, model);
        ValidatorResult<? extends BaseEntity> validatorResult = validator.onEdit(model, webPage.displayLang(request));

        if (validatorResult.getValidationStatus().equals(ValidationStatus.SUCCESS)) {
            webPage.setUserLogged(request, model, true);
            service.update(model);
            return webPage.redirectListPage(modelName);
        } else {
            Map<String, Object> objectMap = webPage.mapWithLoginUser(request);
            objectMap.put("modelName", modelName);
            objectMap.put("model", model);
            objectMap.put("errorMessage", validatorResult.getFieldMessages());
            setReferenceData(objectMap);
            String content = webPage.stringFromVm(packageName + modelName + WebPage.EDIT_PAGE_SUFFIX, objectMap);
            return webPage.okResponse(content);
        }
    }
}
