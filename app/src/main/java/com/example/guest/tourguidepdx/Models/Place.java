package com.example.guest.tourguidepdx.Models;

import android.app.Activity;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Guest on 11/11/15.
 */
@ParseClassName("Place")
public class Place extends ParseObject {

    public static List<Place> mPlace;

    public Place() {
        super();
    }

    public String getName() {
        return getString("name");
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

    public static List<Place> getPlace() {
        return mPlace;
    }

    public static void findAllPlaces(String type, final Activity context, final Runnable runnable) {
        ParseQuery<Place> query = ParseQuery.getQuery(Place.class);
        query.whereEqualTo("type", type);
        query.findInBackground(new FindCallback<Place>() {
            @Override
            public void done(List<Place> places, ParseException e) {
                mPlace = places;
                context.runOnUiThread(runnable);
            }
        });
    }

    public static Place nextPlace (Place place, List<Place> type) {
        int index = type.indexOf(place);
        if (index == type.size() - 1) {
            return type.get(0);
        } else {
            return type.get(index + 1);
        }
    }

    public static Place previousPlace (Place place, List<Place> type) {
        int index = type.indexOf(place);
        if (index == 0) {
            return type.get(0);
        } else {
            return type.get(index - 1 );
        }
    }

}