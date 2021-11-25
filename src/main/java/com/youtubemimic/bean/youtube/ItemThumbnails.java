package com.youtubemimic.bean.youtube;

public class ItemThumbnails {
    ItemThumbnailImage defaultImage;
    ItemThumbnailImage medium;
    ItemThumbnailImage high;

    public ItemThumbnails() {
    }

    public ItemThumbnails(ItemThumbnailImage defaultImage, ItemThumbnailImage medium, ItemThumbnailImage high) {
        this.defaultImage = defaultImage;
        this.medium = medium;
        this.high = high;
    }

    public ItemThumbnailImage getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(ItemThumbnailImage defaultImage) {
        this.defaultImage = defaultImage;
    }

    public ItemThumbnailImage getMedium() {
        return medium;
    }

    public void setMedium(ItemThumbnailImage medium) {
        this.medium = medium;
    }

    public ItemThumbnailImage getHigh() {
        return high;
    }

    public void setHigh(ItemThumbnailImage high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "ItemThumbnails{" +
                "defaultImage=" + defaultImage +
                ", medium=" + medium +
                ", high=" + high +
                '}';
    }
}
