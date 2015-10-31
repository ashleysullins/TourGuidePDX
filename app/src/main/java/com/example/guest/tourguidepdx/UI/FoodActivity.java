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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.tourguidepdx.Models.Food;
import com.example.guest.tourguidepdx.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private TextView mAddFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mAddFood = (TextView) findViewById(R.id.addFood);

        mAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (FoodActivity.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });

    }

}