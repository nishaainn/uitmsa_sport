package com.example.uitmsa_sport;

public class Sport {
    private String name;
    private int imageResId;
    private String description;

    public Sport(String name, int imageResId, String description) {
        this.name = name;
        this.imageResId = imageResId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}
