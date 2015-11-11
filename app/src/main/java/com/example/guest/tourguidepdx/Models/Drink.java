package com.example.guest.tourguidepdx.Models;

import android.app.Activity;
import android.util.Log;

import com.activeandroid.Model;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by ashleysullins on 11/7/15.
 */
@ParseClassName("Drink")
public class Drink extends ParseObject{

    private static List<Drink> mDrinks;

    public Drink() {
        super();
    }

    public String getPlace() {
        return getString("place");
    }

    public String getAddress() {
        return getString("address");
    }

    public String getWebsite() {
        return getString("website");
    }

    public String getImage() {
        return getString("image");
    }

    public String getInfo() {
        return getString("info");
    }

    public static List<Drink> getDrinks(){
        return mDrinks;
    }

    public static void findAllDrinks(final Activity context, final Runnable runnable) {
        ParseQuery<Drink> query = ParseQuery.getQuery(Drink.class);
        query.findInBackground(new FindCallback<Drink>() {
            @Override
            public void done(List<Drink> drinks, ParseException e) {
                mDrinks = drinks;
                context.runOnUiThread(runnable);
            }
        });
    }

    public static Drink nextDrink (Drink drink) {
        int index = mDrinks.indexOf(drink);
        if (index == mDrinks.size() - 1 ) {
            return mDrinks.get(0);
        } else {
            return mDrinks.get(index + 1 );
        }
    }

    public static Drink previousDrink (Drink drink) {
        int index = mDrinks.indexOf(drink);
         if (index == 0) {
            return mDrinks.get(mDrinks.size() - 1);
        } else {
            return mDrinks.get(index - 1 );
        }
    }
}
