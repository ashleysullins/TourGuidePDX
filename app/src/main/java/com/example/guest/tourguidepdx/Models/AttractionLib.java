package com.example.guest.tourguidepdx.Models;

import com.example.guest.tourguidepdx.R;

import java.util.ArrayList;

/**
 * Created by Guest on 10/26/15.
 */
public class AttractionLib {

    private ArrayList<Attraction> mAttractions;

    public AttractionLib() {
        setAttractions();
    }

    private void setAttractions() {
        mAttractions = new ArrayList<>();

        mAttractions.add(new Attraction(
                "Powell's City of Books",
                "NW",
                "http://www.powells.com/",
                R.drawable.powells,
                "The largest bookstore in the world (maybe)."
        ));
    }

    public ArrayList<Attraction> getAttractions() {
        return mAttractions;
    }
}
