package com.example.guest.tourguidepdx;

import android.app.Application;

import com.example.guest.tourguidepdx.Models.Drink;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by ashleysullins on 11/7/15.
 */
public class TourApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Drink.class);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "hbKXNZh2IWPku7YSqx5WxQrk43kU5jKhy363wisz", "MleD7biZ1UWWZgJIddz9UkFeRW55ZMaPTXuuAZdw");

    }

}
