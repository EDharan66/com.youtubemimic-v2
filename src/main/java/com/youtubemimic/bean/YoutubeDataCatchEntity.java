package com.youtubemimic.bean;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.youtubemimic.bean.youtube.YoutubeSearchListResponse;

@Entity
@Cache
public class YoutubeDataCatchEntity {
    @Id
    Long id;
    @Index
    String searchTitle;
    @Index
    String lastUpdated;
    @Index
    YoutubeSearchListResponse jsonDetails;

    public YoutubeDataCatchEntity() {
    }

    public YoutubeDataCatchEntity(Long id, String searchTitle, String lastUpdated, YoutubeSearchListResponse jsonDetails) {
        this.id = id;
        this.searchTitle = searchTitle;
        this.lastUpdated = lastUpdated;
        this.jsonDetails = jsonDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public YoutubeSearchListResponse getJsonDetails() {
        return jsonDetails;
    }

    public void setJsonDetails(YoutubeSearchListResponse jsonDetails) {
        this.jsonDetails = jsonDetails;
    }

    @Override
    public String toString() {
        return "YoutubeDataCatchEntity{" +
                "id=" + id +
                ", searchTitle='" + searchTitle + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", jsonDetails=" + jsonDetails +
                '}';
    }
}
