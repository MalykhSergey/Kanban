package com.example.kanban.result;

public enum SpaceResult implements Result {
    SpaceCreated("Space successfully created!");
    private final String message;

    SpaceResult(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
