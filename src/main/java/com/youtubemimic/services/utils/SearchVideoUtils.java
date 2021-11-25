package com.youtubemimic.services.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.objectify.ObjectifyService;
import com.youtubemimic.bean.YoutubeDataCatchEntity;
import com.youtubemimic.bean.youtube.YoutubeSearchListResponse;
import com.youtubemimic.services.utils.common.YouTubeMimicUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.youtubemimic.auth.UserPropertyConstant.API_KEY;

public class SearchVideoUtils {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private static final String PART = "id,snippet";
    private static final String TYPE = "video";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 24;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Date date = new Date();

    public SearchVideoUtils(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public static SearchVideoUtils getInstance(HttpServletRequest request, HttpServletResponse response) {
        return new SearchVideoUtils(request, response);
    }

    public void searchList(String keyword) throws IOException {
        YoutubeDataCatchEntity entity;
        try {
            if (keyword.equals("")) {
                entity = ObjectifyService.ofy().load().type(YoutubeDataCatchEntity.class).filter("searchTitle", "home").first().now();
                if (entity != null && entity.getSearchTitle().equals("home")) {
                    System.out.println("get form datastore...");
                    YoutubeSearchListResponse jsonDetails = entity.getJsonDetails();
                    YouTubeMimicUtils.writeResponse(this.response, jsonDetails, 200);
                    return;
                }
            }

            entity = new YoutubeDataCatchEntity();
            HttpURLConnection connection = (HttpURLConnection) searchByKeyWord(keyword).openConnection();
            connection.connect();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            YoutubeSearchListResponse object = mapper.readValue(new InputStreamReader(connection.getInputStream()), YoutubeSearchListResponse.class);
            if(keyword.equals("")||keyword.isEmpty()){
                keyword = "home";
            }
//            System.out.println("object = " + object);
            entity.setSearchTitle(keyword);
            entity.setJsonDetails(object);
            entity.setLastUpdated(formatter.format(date));
//            System.out.println("entity = " + entity);
            ObjectifyService.ofy().save().entity(entity).now();
            System.out.println("new call...");
            YouTubeMimicUtils.writeResponse(this.response, object, 200);


        } catch (Exception var3) {
            YouTubeMimicUtils.apiResponseWriter(this.response, "ERROR", "failed load data", "error", null, 400);
            var3.printStackTrace();
            throw var3;
        }
    }

    public void videoList(String videoId) throws IOException {
        try {
            HttpURLConnection connection = (HttpURLConnection) this.searchByVideoId(videoId).openConnection();
            connection.connect();
            YouTubeMimicUtils.writeResponse(this.response, YouTubeMimicUtils.getRequestBodyURL(connection), 200);
        } catch (IOException var3) {
            YouTubeMimicUtils.apiResponseWriter(this.response, "ERROR", "failed load data", "error", null, 400);
            throw var3;
        }
    }

    private URL searchByKeyWord(String queryTerm) throws IOException {
        return new URL("https://www.googleapis.com/youtube/v3/search?part="+PART+",snippet&key="+API_KEY+"&type="+TYPE+"&maxResults="+NUMBER_OF_VIDEOS_RETURNED+"&q=" + queryTerm);
    }

    private URL searchByVideoId(String videoId) throws IOException {
        return new URL("https://www.googleapis.com/youtube/v3/videos?part="+PART+",snippet&key="+API_KEY+"&id=" + videoId);
    }
}
