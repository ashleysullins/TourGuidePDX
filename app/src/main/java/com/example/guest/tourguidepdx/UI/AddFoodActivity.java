package com.example.guest.tourguidepdx.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.tourguidepdx.Models.Food;
import com.example.guest.tourguidepdx.Models.FoodType;
import com.example.guest.tourguidepdx.R;

public class AddFoodActivity extends AppCompatActivity {
    private EditText mFoodName;
    private EditText mFoodAddress;
    private EditText mFoodWebsite;
    private EditText mFoodDescription;
    private Button mFoodSubmit;
    private FoodType mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        mFoodName = (EditText) findViewById(R.id.foodName);
        mFoodAddress = (EditText) findViewById(R.id.foodAddress);
        mFoodWebsite = (EditText) findViewById(R.id.foodWebsite);
        mFoodDescription = (EditText) findViewById(R.id.foodDescription);
        mFoodSubmit = (Button) findViewById(R.id.foodSubmit);

        String name = getIntent().getStringExtra("categoryName");
        mCategory = FoodType.find(name);

        mFoodSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewFood();
                String name = getIntent().getStringExtra("categoryName");
                Intent intent = new Intent(AddFoodActivity.this, FoodActivity.class);
                intent.putExtra("categoryName", name);
                startActivity(intent);
            }
        });

    }

    private void addNewFood() {
        String foodName = mFoodName.getText().toString();
        String foodAddress = mFoodAddress.getText().toString();
        String foodWebsite = mFoodWebsite.getText().toString();
        String foodDescription = mFoodDescription.getText().toString();

        Food newFood = new Food(foodName, mCategory);
        newFood.save();

    }

}
