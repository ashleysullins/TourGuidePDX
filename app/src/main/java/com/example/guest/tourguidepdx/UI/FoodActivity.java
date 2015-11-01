package com.example.guest.tourguidepdx.UI;

import android.app.ListActivity;
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
import com.example.guest.tourguidepdx.Models.FoodType;
import com.example.guest.tourguidepdx.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends ListActivity {

    private TextView mName;
    private TextView mAddress;
    private ImageView mImage;
    private TextView mDescription;
    private TextView mAddFood;
    private ArrayList<String> mFood;
    private ArrayAdapter<String> mAdapter;
    private FoodType mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        String name = getIntent().getStringExtra("categoryName");
        mCategory = FoodType.find(name);

        mFood = new ArrayList<String>();
        for (Food food : mCategory.food() ) {
            mFood.add(food.getName());
        }

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mFood);
        setListAdapter(mAdapter);

//        mName = (TextView) findViewById(R.id.foodName);
//        mAddress = (TextView) findViewById(R.id.foodAddress);
////        mImage = (ImageView) findViewById(R.id.foodImage);
//        mDescription = (TextView) findViewById(R.id.foodDescription);


//        mImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mFood.getWebsite()));
//                startActivity(intent);
//            }
//        });

        mAddFood = (TextView) findViewById(R.id.addFood);

        mAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getIntent().getStringExtra("categoryName");
                Intent intent = new Intent(FoodActivity.this, AddFoodActivity.class);
                intent.putExtra("categoryName", name);
                startActivity(intent);
            }
        });

    }


}