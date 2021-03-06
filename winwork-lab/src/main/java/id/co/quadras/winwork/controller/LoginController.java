package id.co.quadras.winwork.controller;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import id.co.quadras.winwork.model.entity.app.AppRole;
import id.co.quadras.winwork.model.entity.app.AppUser;
import id.co.quadras.winwork.service.app.AppPermissionService;
import id.co.quadras.winwork.service.app.AppRoleService;
import id.co.quadras.winwork.service.app.AppUserService;
import id.co.quadras.winwork.shared.WebPage;
import id.co.quadras.winwork.shared.WebSession;
import id.co.quadras.winwork.util.SecurityUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Path("/login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Context
    HttpServletRequest request;

    @Inject
    private AppRoleService appRoleService;

    @Inject
    private AppUserService appUserService;

    @Inject
    private AppPermissionService appPermissionService;

    @Inject
    private WebPage webPage;

    @Inject
    private WebSession webSession;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response submitLogin(MultivaluedMap<String, String> formMap) {

        Response response = null;
        Map<String, Object> objectMap = new HashMap<String, Object>();

        AppUser filter = new AppUser();
        String username =  formMap.getFirst("username");
        String password = formMap.getFirst("password");

        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            objectMap.put("loginMessage", "Empty Username or Password");
            String content = webPage.stringFromVm(WebPage.COMMON_PAGE_PACKAGE + "login.vm", objectMap);
            response = Response.ok(content).build();
            return response;
        }

        filter.setUsername(username);
        try {
            filter.setPassword(SecurityUtil.createHash(password, SecurityUtil.DEFAULT_HASH));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        filter.setActive(Boolean.TRUE);

        List<AppUser> userList = appUserService.select(filter, null);
        if (userList.isEmpty()) {
            objectMap.put("loginMessage", "Invalid Username or Password");
            String content = webPage.stringFromVm(WebPage.COMMON_PAGE_PACKAGE + "login.vm", objectMap);
            response = Response.ok(content).build();
        } else {
            webSession.createMainWebSession(request);

            AppUser user = appUserService.getById(userList.get(0).getId(), true);
            user.setLastLogin(new DateTime().toDate());
            user.setLastLoginFrom(request.getRemoteAddr());
            appUserService.update(user);

            List<AppRole> initRoleList = new LinkedList<AppRole>();
            List<AppRole> roleList = user.getAppRoleList();
            for (AppRole appRole : roleList) {
                AppRole initRole = appRoleService.getById(appRole.getId(), true);
                initRoleList.add(initRole);
            }

            webSession.set(request, WebSession.LOGIN_USER, user);

            webSession.set(request, WebSession.MENU_LIST,
                    appPermissionService.sortMenu(appPermissionService.getMenuList(initRoleList)));

            webSession.set(request, WebSession.USER_PERMISSION_LIST,
                    appPermissionService.getUserAccessList(initRoleList));

            try {
                response = webPage.redirectResponse("");
            } catch (URISyntaxException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        }

        return response;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response loginPage() {
        String content = webPage.stringFromVm(WebPage.COMMON_PAGE_PACKAGE + "login.vm", null);
        return Response.ok(content).build();
    }

}
