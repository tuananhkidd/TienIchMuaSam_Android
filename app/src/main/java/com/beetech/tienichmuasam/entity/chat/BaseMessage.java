package com.beetech.tienichmuasam.entity.chat;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.Map;

public abstract class BaseMessage {
    public static final String TYPE = "type";
    public static final String OWNER_ID = "ownerID";
    public static final String CREATED_DATE = "createdDate";
    public static final String SEEN = "seen";

    public static final String TEXT_MESSAGE = "text";
    public static final String IMAGE_MESSAGE = "image";
    public static final String EMOJI_MESSAGE = "emoji";
    public static final String LOCATION_MESSAGE = "location";

    private String type;
    private String ownerID;
    @ServerTimestamp
    private Date createdDate;
    private boolean seen = false;
    private Map<String, Boolean> seenBy;


    public Map<String, Boolean> getSeenBy() {
        return seenBy;
    }

    public void setSeenBy(Map<String, Boolean> seenBy) {
        this.seenBy = seenBy;
    }
    public BaseMessage(String ownerID, String type) {
        this.ownerID = ownerID;
        this.type = type;
    }

    public BaseMessage() {
    }

    public void update(BaseMessage baseMessage) {
        setOwnerID(baseMessage.getOwnerID());
        setCreatedDate(baseMessage.getCreatedDate());
        setSeen(baseMessage.getSeen());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean getSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
