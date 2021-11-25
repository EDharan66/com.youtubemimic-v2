package com.youtubemimic.controller.taskqueue;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.youtubemimic.annotations.TrendApiInputChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

@Path("/taskQueue/enqueue")
public class Taskcontroller {

    @POST
    @Path("/trend")
    @TrendApiInputChecker
    public void trendsQueue(@Context UriInfo info, @Context HttpServletRequest request) throws IOException,NullPointerException {

        String videoId = info.getQueryParameters().getFirst("videoId");
        String userId = info.getQueryParameters().getFirst("userId");
        if(userId==null){
            HttpSession session = request.getSession(false);
            if(session!=null && session.getAttribute("userId")!=null){
                userId = (String) session.getAttribute("userId");
            }else {
                throw new NullPointerException("userId is null");
            }
        }
        Queue queue = QueueFactory.getDefaultQueue();
        queue.add(TaskOptions.Builder.withUrl("/api/v1/youtube/trends").param("videoId", videoId).param("userId",userId));

    }
}
