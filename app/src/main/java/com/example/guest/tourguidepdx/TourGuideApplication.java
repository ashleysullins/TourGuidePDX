package com.example.guest.tourguidepdx;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Guest on 11/3/15.
 */
public class TourGuideApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "hbKXNZh2IWPku7YSqx5WxQrk43kU5jKhy363wisz", "MleD7biZ1UWWZgJIddz9UkFeRW55ZMaPTXuuAZdw");

    }
}

