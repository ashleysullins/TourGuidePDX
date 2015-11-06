package com.example.guest.tourguidepdx.UI;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.guest.tourguidepdx.Models.Cuisine;
import com.example.guest.tourguidepdx.R;

import java.util.ArrayList;

public class CuisineActivity extends ListActivity {
    private ArrayList<String> mCuisines;
    private Button mAddCuisineButton;
    private EditText mAddCuisineText;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);

        mCuisines = new ArrayList<String>();
        for (Cuisine cuisine: Cuisine.all()) {
            mCuisines.add(cuisine.getCuisine());
        }

        mAddCuisineButton = (Button) findViewById(R.id.addCategoryButton);
        mAddCuisineText = (EditText) findViewById(R.id.addCategory);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mCuisines);
        setListAdapter(mAdapter);

        mAddCuisineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCuisines();
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        String thisCuisineName = mCuisines.get(position);
        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra("cuisineName", thisCuisineName);
        startActivity(intent);
    }

    private void addCuisines() {
        String name = mAddCuisineText.getText().toString();
        Cuisine cuisine = new Cuisine(name);
        cuisine.save();
        mCuisines.add(name);
        mAdapter.notifyDataSetChanged();
    }

}
