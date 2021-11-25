package com.youtubemimic.bean.youtube;


import java.util.List;

public class YoutubeSearchListResponse {

    String kind;
    String etag;
    String regionCode;
    String nextPageToken;

    PageInfo pageInfo;
    List<Items> items;

    public YoutubeSearchListResponse() {
    }

    public YoutubeSearchListResponse(String kind, String etag, String regionCode, String nextPageToken, PageInfo pageInfo, List<Items> items) {
        this.kind = kind;
        this.etag = etag;
        this.regionCode = regionCode;
        this.nextPageToken = nextPageToken;
        this.pageInfo = pageInfo;
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "YoutubeSearchListResponse{" +
                "\nkind='" + kind + '\'' +
                "\n, etag='" + etag + '\'' +
                "\n, regionCode='" + regionCode + '\'' +
                "\n, nextPageToken='" + nextPageToken + '\'' +
                "\n, pageInfo=" + pageInfo +
                "\n, items=" + items +
                '}';
    }
}

class PageInfo{
    String totalResults;
    String resultsPerPage;

    public PageInfo() {
    }

    public PageInfo(String totalResults, String resultsPerPage) {
        this.totalResults = totalResults;
        this.resultsPerPage = resultsPerPage;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(String resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "\ntotalResults='" + totalResults + '\'' +
                "\n, resultsPerPage='" + resultsPerPage + '\'' +
                '}';
    }
}