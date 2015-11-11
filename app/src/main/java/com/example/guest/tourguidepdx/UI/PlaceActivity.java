package com.example.guest.tourguidepdx.UI;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.tourguidepdx.Models.Place;
import com.example.guest.tourguidepdx.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlaceActivity extends AppCompatActivity {

    float x1, x2;
    float y1, y2;

    @Bind(R.id.placeName) TextView mName;
    @Bind(R.id.placeAddress) TextView mAddress;
    @Bind(R.id.placeImage) ImageView mImage;
    @Bind(R.id.placeDescription) TextView mDescription;

    private Place mPlace;
    private List<Place> mAllPlaces;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        ButterKnife.bind(this);

        // Font path
        String fontPath = "fonts/Pacifico.ttf";

        // text view label
        TextView txtattractionName= (TextView) findViewById(R.id.placeName);

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Applying font
        txtattractionName.setTypeface(tf);

        String type = getIntent().getStringExtra("type");

        Place.findAllPlaces(type, PlaceActivity.this, new Runnable() {
            @Override
            public void run() {
                mAllPlaces = Place.getPlace();
                mPlace = mAllPlaces.get(0);
                setLayoutContent();
            }
        });

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPlace.getWebsite()));
                startActivity(intent);
            }
        });

    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                y2 = touchevent.getY();
            }
            if (x1 < x2) {
                mPlace = Place.previousPlace(mPlace, mAllPlaces);
                setLayoutContent();
            }
            if (x1 > x2) {
                mPlace = Place.nextPlace(mPlace, mAllPlaces);
                setLayoutContent();
            }
            break;
        }
        return false;
    }

    private void setLayoutContent() {
        mName.setText(mPlace.getName());
        mAddress.setText(mPlace.getAddress());
        Picasso.with(this).load(mPlace.getImage()).into(mImage);
        mDescription.setText(mPlace.getInfo());
    }

}