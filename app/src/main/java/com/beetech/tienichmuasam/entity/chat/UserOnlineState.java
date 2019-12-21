package com.beetech.tienichmuasam.entity.chat;

public class UserOnlineState {
    public static final String IS_ONLINE = "isOnline";
    private boolean isOnline;

    public UserOnlineState(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public UserOnlineState() {
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }
}
