package com.example.guest.tourguidepdx.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.tourguidepdx.Models.Cuisine;
import com.example.guest.tourguidepdx.Models.Food;
import com.example.guest.tourguidepdx.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddFoodActivity extends AppCompatActivity {

    @Bind(R.id.foodName) EditText mFoodName;
    @Bind(R.id.foodAddress) EditText mFoodAddress;
    @Bind(R.id.foodWebsite) EditText mFoodWebsite;
    @Bind(R.id.foodDescription) EditText mFoodDescription;
    @Bind(R.id.foodSubmit) Button mFoodSubmit;
    private Cuisine mCuisine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        ButterKnife.bind(this);

        String name = getIntent().getStringExtra("cuisineName");
        mCuisine = Cuisine.find(name);

        mFoodSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewFood();
                String name = getIntent().getStringExtra("cuisineName");
                Intent intent = new Intent(AddFoodActivity.this, FoodActivity.class);
                intent.putExtra("cuisineName", name);
                startActivity(intent);
            }
        });

    }

    private void addNewFood() {
        String foodName = mFoodName.getText().toString();
        String foodAddress = mFoodAddress.getText().toString();
        String foodWebsite = mFoodWebsite.getText().toString();
        String foodDescription = mFoodDescription.getText().toString();

        Food newFood = new Food(foodName, foodWebsite, foodDescription, foodAddress, mCuisine);
        newFood.save();

    }

}
