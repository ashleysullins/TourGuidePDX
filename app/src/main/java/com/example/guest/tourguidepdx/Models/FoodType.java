package com.example.guest.tourguidepdx.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by ashleysullins on 10/31/15.
 */
@Table(name = "FoodType", id = "_id")
public class FoodType extends Model{

    @Column(name = "Category")
    private String mCategory;

    public FoodType(){
        super();
    }

    public FoodType(String category) {
        super();
        mCategory = category;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public static List<FoodType> all() {
        return new Select().from(FoodType.class).execute();
    }

    public List<Food> food() {
        return getMany(Food.class, "FoodType");
    }
}
