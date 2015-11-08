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

import butterknife.Bind;
import butterknife.ButterKnife;

public class CuisineActivity extends ListActivity {
    private ArrayList<String> mCuisines;
    @Bind(R.id.addCategoryButton) Button mAddCuisineButton;
    @Bind(R.id.addCategory)  EditText mAddCuisineText;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);
        ButterKnife.bind(this);

        mCuisines = new ArrayList<String>();
        for (Cuisine cuisine: Cuisine.all()) {
            mCuisines.add(cuisine.getCuisine());
        }

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
