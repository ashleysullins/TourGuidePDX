package com.example.guest.tourguidepdx.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guest.tourguidepdx.Models.Food;
import com.example.guest.tourguidepdx.R;

import java.util.ArrayList;

/**
 * Created by ashleysullins on 11/1/15.
 */
public class FoodAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Food> mFood;

    public FoodAdapter(Context context, ArrayList<Food> food) {
        mContext = context;
        mFood = food;
    }

    @Override
    public int getCount() {
        return mFood.size();
    }

    @Override
    public Object getItem(int position) {
        return mFood.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_food_item, null);
            holder = new ViewHolder();
            holder.foodName = (TextView) convertView.findViewById(R.id.foodName);
            holder.foodAddress = (TextView) convertView.findViewById(R.id.foodAddress);
            holder.foodDescription = (TextView) convertView.findViewById(R.id.foodDescription);
            holder.foodWebsite = (TextView) convertView.findViewById(R.id.foodWebsite);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Food food = mFood.get(position);

        holder.foodName.setText(food.getName());
        holder.foodAddress.setText(food.getAddress());
        holder.foodDescription.setText(food.getDescription());
        holder.foodWebsite.setText(food.getWebsite());

        return convertView;
    }

    public static class ViewHolder {
        TextView foodName;
        TextView foodAddress;
        TextView foodDescription;
        TextView foodWebsite;
    }
}
