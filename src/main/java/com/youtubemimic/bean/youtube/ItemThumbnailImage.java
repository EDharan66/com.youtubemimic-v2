package com.youtubemimic.bean.youtube;

public class ItemThumbnailImage {
    String url;
    Long width;
    Long height;

    public ItemThumbnailImage() {
    }

    public ItemThumbnailImage(String url, Long width, Long height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ItemThumbnailImage{" +
                "url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
