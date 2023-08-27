package com.example.kanban.result;

public enum SpaceResult implements Result {
    SpaceCreated("Space successfully created!");
    private final String message;
    private int spaceId = 0;

    SpaceResult(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }
}
