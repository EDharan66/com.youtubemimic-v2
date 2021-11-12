package com.youtubemimic.controller;

import com.youtubemimic.services.utils.SearchVideoUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;

@Path("/api/v1/youtube")
public class YoutubeController {

    @GET
    @Path("/home")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void redirectHomePage(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/homepageV2.html").include(request, response);
    }

    @GET
    @Path("/search")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void searchByKeyword(@QueryParam("keyword") String keyword,@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        SearchVideoUtils.getInstance(request, response).searchList(keyword);
    }

    @GET
    @Path("/search/video")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public void SearchByVideoId(@QueryParam("videoId") String videoId, @Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        SearchVideoUtils.getInstance(request, response).videoList(videoId);
    }
}
