package com.example.guest.tourguidepdx.UI;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.tourguidepdx.Adapters.FoodAdapter;
import com.example.guest.tourguidepdx.Models.Cuisine;
import com.example.guest.tourguidepdx.Models.Food;
import com.example.guest.tourguidepdx.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodActivity extends ListActivity {

    @Bind(R.id.addFood) TextView mAddFood;
    private ArrayList<Food> mFood;
    private FoodAdapter mAdapter;
    private Cuisine mCuisine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        ButterKnife.bind(this);

        String name = getIntent().getStringExtra("cuisineName");
        mCuisine = Cuisine.find(name);
        mFood = (ArrayList) Food.all(mCuisine.getId());


        mAdapter = new FoodAdapter(this, mFood);
        setListAdapter(mAdapter);

        mAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getIntent().getStringExtra("cuisineName");
                Intent intent = new Intent(FoodActivity.this, AddFoodActivity.class);
                intent.putExtra("cuisineName", name);
                startActivity(intent);
            }
        });

    }


}