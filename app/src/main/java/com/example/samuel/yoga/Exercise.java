package com.example.samuel.yoga;

/**
 * Created by Samuel on 06/09/2017.
 */

public class Exercise {
    private int imageId;
    private String name;

    public Exercise(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
