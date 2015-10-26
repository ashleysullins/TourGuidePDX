package com.example.guest.tourguidepdx.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.tourguidepdx.Models.Attraction;
import com.example.guest.tourguidepdx.Models.AttractionLib;
import com.example.guest.tourguidepdx.R;

public class Attractions extends AppCompatActivity {

    private TextView mName;
    private TextView mAddress;
    private TextView mWebsite;
    private ImageView mImage;
    private TextView mDescription;

    private AttractionLib mAttractionLib;
    private Attraction mAttraction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

    }

}
