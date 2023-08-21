package com.example.kanban.result;

public enum UserResult implements Result {

    UserCreated("User successfully created!"),
    UserIsExists("User already exists!"),
    TooShortPassword("Password must be longer!"),
    TooShortName("Name must be longer!");
    private final String message;

    UserResult(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
