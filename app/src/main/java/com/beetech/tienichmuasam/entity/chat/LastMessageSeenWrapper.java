package com.beetech.tienichmuasam.entity.chat;

public class LastMessageSeenWrapper {
    private SeenWrapper lastMessage;

    public LastMessageSeenWrapper(boolean seen) {
        this.lastMessage = new SeenWrapper(seen);
    }

    public LastMessageSeenWrapper() {
    }

    public SeenWrapper getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(SeenWrapper lastMessage) {
        this.lastMessage = lastMessage;
    }
}
