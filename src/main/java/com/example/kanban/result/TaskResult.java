package com.example.kanban.result;

public enum TaskResult implements Result {
    TaskCreated("Task successfully created!"),
    UsersIsNotExistsInSpace("User isn't exists in space!");
    private final String message;

    TaskResult(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}