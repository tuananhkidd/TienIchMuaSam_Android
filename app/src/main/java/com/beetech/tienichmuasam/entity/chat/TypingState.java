package com.beetech.tienichmuasam.entity.chat;

public class TypingState {
    public static final String IS_TYPING = "isTyping";

    private boolean isTyping;

    public boolean getIsTyping() {
        return isTyping;
    }

    public void setIsTyping(boolean isTyping) {
        this.isTyping = isTyping;
    }
}
