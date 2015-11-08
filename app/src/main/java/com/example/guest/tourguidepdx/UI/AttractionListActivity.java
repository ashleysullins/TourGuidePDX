package com.example.guest.tourguidepdx.UI;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.GestureDetector;
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

import butterknife.Bind;
import butterknife.ButterKnife;

public class AttractionListActivity extends AppCompatActivity {

    @Bind(R.id.attractionName) TextView mName;
    @Bind(R.id.attractionAddress) TextView mAddress;
    @Bind(R.id.attractionImage) ImageView mImage;
    @Bind(R.id.attractionDescription) TextView mDescription;

    private AttractionLib mAttractionLib;
    private Attraction mAttraction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);
        ButterKnife.bind(this);

        // Font path
        String fontPath = "fonts/Pacifico.ttf";

        // text view label
        TextView txtattractionName= (TextView) findViewById(R.id.attractionName);

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Applying font
        txtattractionName.setTypeface(tf);

        mAttractionLib = new AttractionLib();
        mAttraction = mAttractionLib.getAttractions().get(0);
        setLayoutContent();


        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mAttraction.getWebsite()));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return gestureDetector.onTouchEvent(event);
    }

    GestureDetector.SimpleOnGestureListener simpleOnGestureListener
            = new GestureDetector.SimpleOnGestureListener(){


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            String swipe = "";
            float sensitvity = 50;

            if((e1.getX() - e2.getX()) > sensitvity) {
                mAttraction = mAttractionLib.nextAttraction(mAttraction);
                setLayoutContent();
            }else if((e2.getX() - e1.getX()) > sensitvity){
                    mAttraction = mAttractionLib.previousAttraction(mAttraction);
                    setLayoutContent();
            }else{
                return false;
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };

    GestureDetector gestureDetector
            = new GestureDetector(simpleOnGestureListener);

    private void setLayoutContent() {
        mName.setText(mAttraction.getName());
        mAddress.setText(mAttraction.getAddress());
        mImage.setImageResource(mAttraction.getImage());
        mDescription.setText(mAttraction.getDescription());
    }

}