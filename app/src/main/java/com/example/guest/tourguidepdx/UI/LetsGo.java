package com.example.guest.tourguidepdx.UI;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.tourguidepdx.R;

public class LetsGo extends AppCompatActivity {

    private Button mFoodButton;
    private Button mDrinkButton;
    private Button mAttractionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_go);

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


        mFoodButton = (Button) findViewById(R.id.foodButton);

        mFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LetsGo.this, Food.class);
                startActivity(intent);
            }
        });

        mDrinkButton = (Button) findViewById(R.id.drinkButton);

        mDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LetsGo.this, Drink.class);
                startActivity(intent);
            }
        });

        mAttractionButton = (Button) findViewById(R.id.attractionsButton);

        mAttractionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LetsGo.this, Attractions.class);
                startActivity(intent);
            }
        });
    }


}
