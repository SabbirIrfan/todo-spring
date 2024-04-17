package com.dsi.todo.controller;

public class MyErrorResponse {
    private String title;
    private String message;

    public MyErrorResponse(String title, String message) {
        this.title = title;
        this.message = message;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
