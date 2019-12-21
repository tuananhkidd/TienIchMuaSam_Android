package com.beetech.tienichmuasam.entity.chat;


public class LastMessageWrapper {
    private BaseMessage lastMessage;

    public LastMessageWrapper(BaseMessage lastMessage) {
        this.lastMessage = lastMessage;
    }

    public LastMessageWrapper() {
    }

    public BaseMessage getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(BaseMessage lastMessage) {
        this.lastMessage = lastMessage;
    }
}
