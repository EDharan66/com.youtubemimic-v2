package com.youtubemimic.bean;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Set;

@Entity
public class TrendsEntity {

    @Id
    Long id;
    @Index String videoId;
    @Index
    Set<UserViewDetails> userDetails;
    @Index int trendsCount;

    public TrendsEntity() {
    }

    public TrendsEntity(Long id, String videoId, Set<UserViewDetails> userDetails, int trendsCount) {
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

    public Set<UserViewDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Set<UserViewDetails> userDetails) {
        this.userDetails = userDetails;
    }

    public int getTrendsCount() {
        return trendsCount;
    }

    public void setTrendsCount(int trendsCount) {
        this.trendsCount = trendsCount;
    }

    @Override
    public String toString() {
        return "TrendsEntity{" +
                "id=" + id +
                ", videoId='" + videoId + '\'' +
                ", userDetails=" + userDetails +
                ", trendsCount='" + trendsCount + '\'' +
                '}';
    }
}
