package com.youtubemimic.controller;

import com.youtubemimic.services.utils.SearchVideoUtils;
import com.youtubemimic.services.utils.TrendingVideoUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

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

    @GET
    @Path("/trends")
    @Produces("application/json; charset=utf-8")
    @Consumes("application/json; charset=utf-8")
    public Map<Integer, Set<String>> trendingVideos(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        return TrendingVideoUtils.getInstance(request, response).processGet();
    }

    @POST
    @Path("/trends")
    public void uploadTrendingVideos(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        System.out.println("task working...");
        TrendingVideoUtils.getInstance(request, response).processPost();
    }


}
