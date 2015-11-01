package com.example.guest.tourguidepdx.UI;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.guest.tourguidepdx.Models.FoodType;
import com.example.guest.tourguidepdx.R;

import java.util.ArrayList;

public class FoodTypeActivity extends ListActivity {
    private ArrayList<String> mCategories;
    private Button mAddCategoryButton;
    private EditText mAddCategoryText;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);

        mCategories = new ArrayList<String>();
        for (FoodType category: FoodType.all()) {
            mCategories.add(category.getCategory());
        }

        mAddCategoryButton = (Button) findViewById(R.id.addCategoryButton);
        mAddCategoryText = (EditText) findViewById(R.id.addCategory);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mCategories);
        setListAdapter(mAdapter);

        mAddCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategory();
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        String thisCategoryName = mCategories.get(position);
        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra("categoryName", thisCategoryName);
        startActivity(intent);
    }

    private void addCategory() {
        String name = mAddCategoryText.getText().toString();
        FoodType category = new FoodType(name);
        category.save();
        mCategories.add(name);
        mAdapter.notifyDataSetChanged();
    }

}
