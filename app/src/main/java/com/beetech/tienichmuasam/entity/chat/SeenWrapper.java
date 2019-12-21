package com.beetech.tienichmuasam.entity.chat;

public class SeenWrapper {
    private boolean seen;

    public SeenWrapper() {
    }

    public boolean getSeen() {

        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public SeenWrapper(boolean seen) {
        this.seen = seen;
    }
}
