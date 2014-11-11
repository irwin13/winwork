package com.irwin13.winwork.lab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.irwin13.winwork.lab.service.WebPage;

/**
 * @author irwin Timestamp : 05/07/13 14:09 *
 */
@Path("/publicAccess/noAccess")
public class NoAccessController {

    @Context
    HttpServletRequest request;

    @Inject
    private WebPage webPage;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response leftMenu() {
        String content = webPage.stringFromVm(WebPage.COMMON_PAGE_PACKAGE + "noAccess.vm", webPage.mapWithLoginUser(request));
        return Response.ok(content).build();
    }

}
