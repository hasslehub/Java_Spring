package com.example.WebTask.model;

public class Factory {
    public static Task createNew(String description, TaskStatus status) {
        return new Task(description, status);
    }
}
