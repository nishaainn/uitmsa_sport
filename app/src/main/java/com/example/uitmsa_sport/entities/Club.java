package com.example.uitmsa_sport.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.PropertyName;

public class Club {
    private int mData;
    private String id;
    private String name;
    private String iconURL;
    private String imageURL;

    // Required no-argument constructor
    public Club() {
        // Default constructor required for Firestore
    }

    public Club(String id, String name, String imageURL) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
    }

    @PropertyName("id")
    public String getId() {
        return id;
    }

    @PropertyName("id")
    public void setId(String id) {
        this.id = id;
    }

    @PropertyName("name")
    public String getName() {
        return name;
    }

    @PropertyName("name")
    public void setName(String name) {
        this.name = name;
    }

    @PropertyName("iconURL")
    public String getIconURL() {
        return iconURL;
    }

    @PropertyName("iconURL")
    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    @PropertyName("imageURL")
    public String getImageURL() {
        return imageURL;
    }

    @PropertyName("imageURL")
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}