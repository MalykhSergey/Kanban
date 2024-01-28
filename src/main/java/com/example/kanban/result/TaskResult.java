package com.example.kanban.result;

public enum TaskResult implements Result {
    TaskCreated("Task successfully created!"),
    UsersIsNotExistsInSpace("User isn't exists in space!"),
    TaskUpdated("Task successfully updated!"),
    TaskDeleted("Task successfully deleted!");
    private final String message;

    TaskResult(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
