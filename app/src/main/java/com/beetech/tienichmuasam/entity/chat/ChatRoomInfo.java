package com.beetech.tienichmuasam.entity.chat;


import java.io.Serializable;


public class ChatRoomInfo implements Serializable {
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String VISIT_STATES = "visitStates";
    public static final String TYPING_STATES = "typingStates";
    public static final String LAST_MESSAGE = "lastMessage";
    public static final String MESSAGES = "messages";

    private String id;
    private UserInfo from;
    private UserInfo to;

    public ChatRoomInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserInfo getFrom() {
        return from;
    }

    public void setFrom(UserInfo from) {
        this.from = from;
    }

    public UserInfo getTo() {
        return to;
    }

    public void setTo(UserInfo to) {
        this.to = to;
    }
}
