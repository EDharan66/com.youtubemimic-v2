package com.youtubemimic.services.filter;

import com.youtubemimic.annotations.TrendApiInputChecker;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

@TrendApiInputChecker
@Priority(Priorities.AUTHENTICATION)
public class TrendsApiInputConfig implements ContainerRequestFilter {

    @Context
    UriInfo info;

    @Context
    HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext requestContext) throws NullPointerException {
        String videoId = info.getQueryParameters().getFirst("videoId");
        if(videoId==null){
            throw new NullPointerException("values are null");
        }
    }
}
