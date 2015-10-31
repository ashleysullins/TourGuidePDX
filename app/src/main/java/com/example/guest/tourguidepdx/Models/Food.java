package com.example.guest.tourguidepdx.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by ashleysullins on 10/30/15.
 */

@Table(name = "Food", id = "_id")
public class Food extends Model {

    @Column(name = "Name")
    private String mName;

    @Column(name = "Address")
    private String mAddress;

    @Column(name = "Website")
    private String mWebsite;

    @Column(name = "Image")
    private int mImage;

    @Column(name =  "Description")
    private String mDescription;

    public Food() {
        super();
    }

    public Food(String name, String address, String website, int image, String description) {
        super();
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
        this.mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String website) {
        this.mWebsite = website;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        this.mImage = image;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public static List<Food> all() {
        return new Select().from(Food.class).execute();
    }
}