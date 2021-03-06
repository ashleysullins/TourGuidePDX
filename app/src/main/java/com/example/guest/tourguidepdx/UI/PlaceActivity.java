package com.example.guest.tourguidepdx.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.tourguidepdx.Models.Place;
import com.example.guest.tourguidepdx.R;
import com.parse.ParseGeoPoint;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import im.delight.android.location.SimpleLocation;

public class PlaceActivity extends AppCompatActivity {

    float x1, x2;
    float y1, y2;

    @Bind(R.id.placeName) TextView mName;
    @Bind(R.id.placeAddress) TextView mAddress;
    @Bind(R.id.placeImage) ImageView mImage;
    @Bind(R.id.placeDescription) TextView mDescription;
    @Bind(R.id.mapButton) ImageButton mMapButton;
    @Bind(R.id.searchButton) ImageButton mSearchButton;
    @Bind(R.id.phoneButton) ImageButton mPhoneButton;
    @Bind(R.id.reloadButton) ImageButton mReloadButton;

    private Place mPlace;
    private List<Place> mAllPlaces;
    private SimpleLocation mLocation;
    private double getLat;
    private double getLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        ButterKnife.bind(this);

        // Activate custom font for name of place
        String fontPath = "fonts/Pacifico.ttf";
        TextView txtattractionName= (TextView) findViewById(R.id.placeName);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        txtattractionName.setTypeface(tf);

        onCoachMark();

        mLocation = new SimpleLocation(this);
        if (!mLocation.hasLocationEnabled()) {
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("To see places near you, this app will need to know your location.")
                    .setTitle("Geolocation Settings")
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SimpleLocation.openSettings(PlaceActivity.this);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            builder.show();
        }

        if (mLocation.getLatitude() == 0 || mLocation.getLatitude() < 44 || mLocation.getLatitude() > 46) {
            getLat = 45.520705;
        } else {
            getLat = mLocation.getLatitude();
        }

        if (mLocation.getLongitude() == 0 || mLocation.getLongitude() < -121 || mLocation.getLongitude() > -123) {
             getLong = -122.677397;
        } else {
             getLong = mLocation.getLongitude();
        }

        final double latitude = getLat;
        final double longitude = getLong;

        final String type = getIntent().getStringExtra("type");
        final ParseGeoPoint point = new ParseGeoPoint(latitude, longitude);

        showPlaces(type, point);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPlace.getWebsite()));
                startActivity(intent);
            }
        });

        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:0,0?q=" + mPlace.getAddress());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });

        mPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: " + mPlace.getPhone()));
                startActivity(intent);
            }
        });

        mReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlaces(type, point);
            }
        });

    }

    private void showPlaces(String type, ParseGeoPoint point) {
        Place.findAllPlaces(type, point, PlaceActivity.this, new Runnable() {
            @Override
            public void run() {
                try {
                    mAllPlaces = Place.getPlace();
                    mPlace = mAllPlaces.get(0);
                } catch (Exception e) {
                    errorHandling();
                }
                    if (mPlace == null) {
                    errorHandling();
                } else {
                    setLayoutContent();
                    mReloadButton.setVisibility(View.INVISIBLE);
                }
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
                try {
                    mPlace = Place.previousPlace(mPlace, mAllPlaces);
                }
                catch (NullPointerException e) {
                    errorHandling();
                }  if (mPlace != null) {
                    setLayoutContent();
                }
            }
            if (x1 > x2) {
                try {
                    mPlace = Place.nextPlace(mPlace, mAllPlaces);
                }
                catch (NullPointerException e) {
                    errorHandling();
                }
                if (mPlace != null) {
                    setLayoutContent();
                }
            }
            break;
        }
        return false;
    }

    private void errorHandling() {
        mReloadButton.setVisibility(View.VISIBLE);
        mName.setText("Error!");
        mDescription.setText("There has been an error. Please press refresh or press back button to go back.");
    }

    private void setLayoutContent() {
        mName.setText(mPlace.getName());
        mAddress.setText(mPlace.getAddress());
        Picasso.with(this).load(mPlace.getImage()).into(mImage);
        mDescription.setText(mPlace.getInfo());
    }

    public void onCoachMark(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.coach_mark);
        dialog.setCanceledOnTouchOutside(true);
        //for dismissing anywhere you touch
        View masterView = dialog.findViewById(R.id.coach_mark_master_view);
        masterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}