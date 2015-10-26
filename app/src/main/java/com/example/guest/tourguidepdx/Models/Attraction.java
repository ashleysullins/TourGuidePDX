package com.example.guest.tourguidepdx.Models;

/**
 * Created by Guest on 10/26/15.
 */
public class Attraction {
    private String mName;
    private String mAddress;
    private String mWebsite;
    private int mImage;
    private String mDescription;

    public Attraction(String name, String address, String website, int image, String description) {
        mName = name;
        mAddress = address;
        mWebsite = website;
        mImage = image;
        mDescription = description;

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String website) {
        mWebsite = website;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}

