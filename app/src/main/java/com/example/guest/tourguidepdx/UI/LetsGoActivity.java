package com.example.guest.tourguidepdx.UI;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.guest.tourguidepdx.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LetsGoActivity extends AppCompatActivity {

    @Bind(R.id.foodButton) ImageButton mFoodButton;
    @Bind(R.id.drinkButton) ImageButton mDrinkButton;
    @Bind(R.id.attractionsButton) ImageButton mAttractionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_go);
        ButterKnife.bind(this);

        // Font path
        String fontPath = "fonts/Pacifico.ttf";

        // text view label
        TextView txtBeforeText= (TextView) findViewById(R.id.beforeText);
        TextView txtCategoryText= (TextView) findViewById(R.id.categoryText);

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Applying font
        txtBeforeText.setTypeface(tf);
        txtCategoryText.setTypeface(tf);

        mFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LetsGoActivity.this, PlaceActivity.class);
                intent.putExtra("type", "food");
                startActivity(intent);
            }
        });

        mDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LetsGoActivity.this, PlaceActivity.class);
                intent.putExtra("type", "drink");
                startActivity(intent);
            }
        });

        mAttractionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LetsGoActivity.this, PlaceActivity.class);
                intent.putExtra("type", "attraction");
                startActivity(intent);
            }
        });
    }


}
