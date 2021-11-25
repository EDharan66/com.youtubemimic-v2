package com.youtubemimic.bean.youtube;

public class Items {

    String kind;
    String etag;
    ItemId id;
    ItemSnippet snippet;


    public Items() {
    }

    public Items(String kind, String etag, ItemId id, ItemSnippet snippet) {
        this.kind = kind;
        this.etag = etag;
        this.id = id;
        this.snippet = snippet;

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

    public ItemId getId() {
        return id;
    }

    public void setId(ItemId id) {
        this.id = id;
    }

    public ItemSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(ItemSnippet snippet) {
        this.snippet = snippet;
    }


    @Override
    public String toString() {
        return "Items{" +
                "\tkind='" + kind + '\'' +
                "\t\n, etag='" + etag + '\'' +
                "\t\n, id=" + id +
                "\t\n, snippet=" + snippet +
                '}';
    }
}

class ItemId {
    String kind;
    String videoId;

    public ItemId() {
    }

    public ItemId(String kind, String videoId) {
        this.kind = kind;
        this.videoId = videoId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}







