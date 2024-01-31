package com.example.kanban.result;

public enum SpaceResult implements Result {
    SpaceCreated("Space successfully created!"),
    UserAdded("User successfully added to Space!"),
    UserDeleted("User successfully deleted to Space!"),
    UserIsNotExistsInSpace("User isn't exists in space!"),
    NotExists("Space isn't exists!"), Deleted("Space successfully created!");

    private final String message;

    SpaceResult(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
