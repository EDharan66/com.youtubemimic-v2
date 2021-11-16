package com.youtubemimic.controller;

import com.youtubemimic.annotations.UserIdentifier;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/api/")
public class RedirectController {
    @GET
    @Path("/redirect")
    @UserIdentifier
    @Produces("application/json; charset=utf-8") @Consumes("application/json; charset=utf-8")
    public Response redirect(){
        return Response.created(URI.create("/api/v1/youtube/home")).build();
    }
}
