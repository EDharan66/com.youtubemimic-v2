package com.youtubemimic.services.utils;

import com.googlecode.objectify.ObjectifyService;
import com.youtubemimic.bean.TrendsEntity;
import com.youtubemimic.bean.UserViewDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TrendingVideoUtils {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public TrendingVideoUtils(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public static TrendingVideoUtils getInstance(HttpServletRequest request, HttpServletResponse response) {
        return new TrendingVideoUtils(request, response);
    }

    public Map<Integer, Set<String>> processGet(){

        List<TrendsEntity> listEntity = ObjectifyService.ofy().load().type(TrendsEntity.class).list();

        //.filter(list->list.getTrendsCount()>5)
        return listEntity.stream()
                .collect(Collectors.groupingBy(TrendsEntity::getTrendsCount,
                        Collectors.mapping(TrendsEntity::getVideoId,Collectors.toSet())));
    }

    public void processPost() throws NullPointerException{

//        System.out.println("info = " + info);
//        System.out.println("info.getQueryParameters(\"videoId\") = " + info.getQueryParameters().getFirst("videoId"));
//        System.out.println("info.getQueryParameters(\"videoId\") = " + info.getQueryParameters().getFirst("userId"));

        try {
            int count;
            Set<UserViewDetails> userDetails;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String videoId = request.getParameter("videoId");
            String userId = request.getParameter("userId");

            if(videoId==null||userId==null){
                throw new NullPointerException("inputs are null");
            }

            TrendsEntity entity = ObjectifyService.ofy().load().type(TrendsEntity.class).filter("videoId",videoId).first().now();

            if(entity!=null){
                count = entity.getTrendsCount();
                Boolean newUser=true;

                userDetails = entity.getUserDetails();
                for (UserViewDetails details: userDetails) {
                    if(details.getUserId()==Long.parseLong(userId)){
                        int userViewCount = details.getCount();
                        details.setCount(++userViewCount);
                        details.setLastSeen(formatter.format(date));
                        newUser = false;
                        break;
                    }
                }

                if(userDetails.isEmpty()|| newUser){
                    UserViewDetails viewDetails = getUserViewDetails(formatter, date, userId);

                    userDetails.add(viewDetails);
                    entity.setTrendsCount(++count);
//                userDetails = Collections.singletonList(viewDetails);
                }
            }else {
                entity = new TrendsEntity();
                entity.setVideoId(videoId);
                entity.setTrendsCount(1);

                UserViewDetails viewDetails = getUserViewDetails(formatter, date, userId);

                entity.setUserDetails(Collections.singleton(viewDetails));

            }

            System.out.println("entity.toString() = " + entity.toString());
            ObjectifyService.ofy().save().entity(entity).now();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private UserViewDetails getUserViewDetails(SimpleDateFormat formatter, Date date, String userId) {
        UserViewDetails viewDetails = new UserViewDetails();
        viewDetails.setUserId(Long.parseLong(userId));
        viewDetails.setCount(1);
        viewDetails.setLastSeen(formatter.format(date));
        return viewDetails;
    }


}
