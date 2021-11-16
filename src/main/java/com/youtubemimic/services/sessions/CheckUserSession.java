package com.youtubemimic.services.sessions;

import com.youtubemimic.annotations.UserIdentifier;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;


@UserIdentifier
@Priority(Priorities.AUTHENTICATION)
public class CheckUserSession implements ContainerRequestFilter {

    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        HttpSession httpSession = request.getSession(false);

        if (httpSession == null || httpSession.getAttribute("userId")==null) {
            requestContext.abortWith(Response.created(URI.create("/indexV2.html")).status(302).build());
        }else {
            System.out.println("httpSession = " + httpSession);
            System.out.println("httpSession.getAttribute(\"userId\") = " + httpSession.getAttribute("userId"));
        }
    }


}
