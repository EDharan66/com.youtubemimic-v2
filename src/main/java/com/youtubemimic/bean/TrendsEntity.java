package com.youtubemimic.bean;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.List;

@Entity
public class TrendsEntity {

    @Id
    Long id;
    @Index String videoId;
    @Index
    List<UserViewDetails> userDetails;
    @Index String trendsCount;

    public TrendsEntity(Long id, String videoId, List<UserViewDetails> userDetails, String trendsCount) {
        this.id = id;
        this.videoId = videoId;
        this.userDetails = userDetails;
        this.trendsCount = trendsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public List<UserViewDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserViewDetails> userDetails) {
        this.userDetails = userDetails;
    }

    public String getTrendsCount() {
        return trendsCount;
    }

    public void setTrendsCount(String trendsCount) {
        this.trendsCount = trendsCount;
    }
}
