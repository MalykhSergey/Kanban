package com.example.kanban.result;

public enum SpaceResult implements Result {
    SpaceCreated("Space successfully created!"),
    UserAdded("User successfully added to Space!");
    private final String message;
    SpaceResult(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
