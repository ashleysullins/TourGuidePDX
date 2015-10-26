package com.example.guest.tourguidepdx.UI;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.tourguidepdx.Models.Attraction;
import com.example.guest.tourguidepdx.Models.AttractionLib;
import com.example.guest.tourguidepdx.R;

import org.w3c.dom.Text;

public class AttractionList extends AppCompatActivity {

    private TextView mName;
    private TextView mAddress;
    private ImageView mImage;
    private TextView mDescription;

    private AttractionLib mAttractionLib;
    private com.example.guest.tourguidepdx.Models.Attraction mAttraction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        mName = (TextView) findViewById(R.id.attractionName);
        mAddress = (TextView) findViewById(R.id.attractionAddress);
        mImage = (ImageView) findViewById(R.id.attractionImage);
        mDescription = (TextView) findViewById(R.id.attractionDescription);
        mAttractionLib = new AttractionLib();
        mAttraction = mAttractionLib.getAttractions().get(0);

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mAttraction.getWebsite()));
                startActivity(intent);
            }
        });

        mDescription.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    mAttraction = mAttractionLib.nextAttraction(mAttraction);
                    setLayoutContent();
                }
                return false;
            }
        });

    }

    private void setLayoutContent() {
        mName.setText(mAttraction.getName());
        mAddress.setText(mAttraction.getAddress());
        mImage.setImageResource(mAttraction.getImage());
        mDescription.setText(mAttraction.getDescription());
    }

}
