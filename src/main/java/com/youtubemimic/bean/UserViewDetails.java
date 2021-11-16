package com.youtubemimic.bean;

public class UserViewDetails {
    Long userId;
    int count;
    String lastSeen;

    public UserViewDetails(Long userId, int count, String lastSeen) {
        this.userId = userId;
        this.count = count;
        this.lastSeen = lastSeen;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }
}
