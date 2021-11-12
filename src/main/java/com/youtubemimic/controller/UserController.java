package com.youtubemimic.controller;

import com.youtubemimic.services.utils.UserProfileUtils;
import com.youtubemimic.services.utils.UserUtils;
import com.youtubemimic.services.utils.common.YouTubeMimicUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

@Path("/api/v1/user")
public class UserController {

    @GET @Path("/profile") @Produces("application/json; charset=utf-8") @Consumes("application/json; charset=utf-8")
    public void processGetProfile(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        UserProfileUtils.getInstance(request, response).processGet();
    }

    @PUT
    @Path("/profile")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void processUpdateProfile(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        UserProfileUtils.getInstance(request, response).processPut();
    }

    @DELETE
    @Path("/profile")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void processDeleteProfile(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        UserProfileUtils.getInstance(request, response).processDelete();
    }

    @POST
    @Path("/login")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void userLogin(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        UserUtils.getInstance(request, response).processLogin();
    }

    @POST
    @Path("/signup")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void userSignUp(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        UserUtils.getInstance(request, response).processSignUp();
    }

    @POST
    @Path("/logout")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void userLogout(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        YouTubeMimicUtils.signOut(request, response);
    }
}
