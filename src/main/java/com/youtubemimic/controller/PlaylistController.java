package com.youtubemimic.controller;

import com.youtubemimic.services.utils.PlaylistUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

@Path("/api/v1")
public class PlaylistController {

    @GET
    @Path("/playlist")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void processGetList(@QueryParam("playlistName") String playlistName,@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        PlaylistUtils.getInstance(request, response).processGet(playlistName);
    }

    @POST
    @Path("/playlist")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void processCreateList(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        PlaylistUtils.getInstance(request, response).processPost();
    }

    @PUT
    @Path("/playlist")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void processUpdateList(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        PlaylistUtils.getInstance(request, response).processPut();
    }

    @DELETE
    @Path("/playlist")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void processDeleteList(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        PlaylistUtils.getInstance(request, response).processDelete();
    }


    @DELETE
    @Path("/playlist/video")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void processDeleteVideo(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        PlaylistUtils.getInstance(request, response).processVideoDelete();
    }
}
