package com.example.guest.tourguidepdx.Models;

import com.example.guest.tourguidepdx.R;

import org.w3c.dom.Attr;

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

        mAttractions.add(new Attraction(
                "Lan Su Chinese Garden",
                "Chinatown",
                "http://www.lansugarden.org/",
                R.drawable.lansu,
                "Chinese garden in SW portland. The only attraction in Chinatown."
        ));

        mAttractions.add(new Attraction(
                "Washington Park",
                "Take the Red Line until you get there",
                "http://washingtonparkpdx.org/",
                R.drawable.washington,
                "Includes the Zoo, Japanese Garden, Rose garden, Hoyt Arboretum"
        ));

        mAttractions.add(new Attraction(
                "The Grotto",
                "8840 NE Skidmore Street",
                "http://thegrotto.org/",
                R.drawable.grotto,
                "Catholic botanical garden. Christmas Festival of Lights. Friday after Thanksgiving thru December 30."
        ));
    }

    public ArrayList<Attraction> getAttractions() {
        return mAttractions;
    }

    public Attraction nextAttraction (Attraction attraction) {
        int index = mAttractions.indexOf(attraction);
        if (index == mAttractions.size() - 1 ){
            return mAttractions.get(0);
        } else {
            return mAttractions.get(index + 1);
        }
    }

    public Attraction previousAttraction (Attraction attraction) {
        int index = mAttractions.indexOf(attraction);
        if (index == 0) {
            return mAttractions.get(mAttractions.size() - 1);
        } else {
            return mAttractions.get(index - 1);
        }
    }
}
