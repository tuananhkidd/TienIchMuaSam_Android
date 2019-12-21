package com.beetech.tienichmuasam.entity.chat;

public class OnlineStateAndPosition {
    private boolean isOnline;
    private int position;

    public OnlineStateAndPosition(int position) {
        this.isOnline = false;
        this.position = position;
    }

    public OnlineStateAndPosition(boolean isOnline) {
        this.isOnline = isOnline;
        this.position = -1;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
