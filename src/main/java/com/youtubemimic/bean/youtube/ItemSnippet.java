package com.youtubemimic.bean.youtube;

public class ItemSnippet {
    String publishedAt;
    String channelId;
    String title;
    String description;
    String channelTitle;
    String liveBroadcastContent;
    String publishTime;
    ItemThumbnails thumbnails;

    public ItemSnippet() {
    }

    public ItemSnippet(String publishedAt, String channelId, String title, String description, String channelTitle, String liveBroadcastContent, String publishTime, ItemThumbnails thumbnails) {
        this.publishedAt = publishedAt;
        this.channelId = channelId;
        this.title = title;
        this.description = description;
        this.channelTitle = channelTitle;
        this.liveBroadcastContent = liveBroadcastContent;
        this.publishTime = publishTime;
        this.thumbnails = thumbnails;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public ItemThumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ItemThumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    @Override
    public String toString() {
        return "ItemSnippet{" +
                "publishedAt='" + publishedAt + '\'' +
                ", channelId='" + channelId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", channelTitle='" + channelTitle + '\'' +
                ", liveBroadcastContent='" + liveBroadcastContent + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", thumbnails=" + thumbnails +
                '}';
    }
}
