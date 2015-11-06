package com.example.guest.tourguidepdx.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by ashleysullins on 10/31/15.
 */
@Table(name = "Cuisine", id = "_id")
public class Cuisine extends Model{

    @Column(name = "Cuisine")
    private String mCuisine;

    public Cuisine(){
        super();
    }

    public Cuisine(String cuisine) {
        super();
        mCuisine = cuisine;
    }

    public String getCuisine() {
        return mCuisine;
    }

    public void setCategory(String cuisines) {
        this.mCuisine = cuisines;
    }

    public static List<Cuisine> all() {
        return new Select().from(Cuisine.class).execute();
    }

    public List<Food> food() {
        return getMany(Food.class, "Cuisine");
    }

    public static Cuisine find(String cuisine) {
        return new Select()
                .from(Cuisine.class)
                .where("Cuisine = ? ", cuisine)
                .executeSingle();
    }
}
