package com.youtubemimic.services.resteasy.config;

import com.googlecode.objectify.ObjectifyService;
import com.youtubemimic.bean.PlaylistEntity;
import com.youtubemimic.bean.UserDataEntity;
import com.youtubemimic.controller.PlaylistController;
import com.youtubemimic.controller.RedirectController;
import com.youtubemimic.controller.UserController;
import com.youtubemimic.controller.YoutubeController;
import com.youtubemimic.services.sessions.CheckUserSession;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


public class RestEasyServices extends Application {

    static {
        ObjectifyService.init();
        ObjectifyService.register(UserDataEntity.class);
        ObjectifyService.register(PlaylistEntity.class);
    }

    private final Set<Object> singletons = new HashSet<Object>();
    private final Set<Class<?>> classes = new HashSet<Class<?>>();

    public RestEasyServices() {
//        singletons.add(new PlaylistController());
//        singletons.add(new YoutubeController());
//        singletons.add(new UserController());
    }

    @Override
    public Set<Class<?>> getClasses()
    {
        classes.add(UserController.class);
        classes.add(YoutubeController.class);
        classes.add(PlaylistController.class);
        classes.add(RedirectController.class);
        classes.add(CheckUserSession.class);

        return classes;
    }

//    @Override
//    public Set<Object> getSingletons() {
//        return singletons;
//    }
}
