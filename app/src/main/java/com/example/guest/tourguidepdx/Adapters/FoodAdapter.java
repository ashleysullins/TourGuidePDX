package com.example.guest.tourguidepdx.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.tourguidepdx.Models.Food;
import com.example.guest.tourguidepdx.R;
import com.example.guest.tourguidepdx.UI.FoodTypeActivity;

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
            holder.foodWebsite = (ImageView) convertView.findViewById(R.id.foodWebsite);
            holder.foodDelete = (ImageView) convertView.findViewById(R.id.foodDelete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Food food = mFood.get(position);

        holder.foodName.setText(food.getName());
        holder.foodAddress.setText(food.getAddress());
        holder.foodDescription.setText(food.getDescription());
        holder.foodWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(food.getWebsite()));
                mContext.startActivity(intent);
            }
        });

        holder.foodDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                food.delete();
                                Intent intent = new Intent(mContext, FoodTypeActivity.class);
                                mContext.startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog show = new AlertDialog.Builder(mContext)
                        .setTitle("Alert")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        TextView foodName;
        TextView foodAddress;
        TextView foodDescription;
        ImageView foodWebsite;
        ImageView foodDelete;
    }
}
