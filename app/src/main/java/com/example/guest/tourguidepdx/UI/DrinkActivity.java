package com.example.guest.tourguidepdx.UI;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.tourguidepdx.Models.Attraction;
import com.example.guest.tourguidepdx.Models.Drink;
import com.example.guest.tourguidepdx.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrinkActivity extends AppCompatActivity {

    float x1, x2;
    float y1, y2;
    float diffx, diffy;

    @Bind(R.id.drinkName) TextView mDrinkName;
    @Bind(R.id.drinkAddress) TextView mDrinkAddress;
    @Bind(R.id.drinkImage) ImageView mDrinkImage;
    @Bind(R.id.drinkDescription) TextView mDrinkDescription;

    private Drink mDrink;
    protected List<Drink> mAllDrinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        ButterKnife.bind(this);

        Drink.findAllDrinks(DrinkActivity.this, new Runnable() {
            @Override
            public void run() {
                mAllDrinks = Drink.getDrinks();
                mDrink = mAllDrinks.get(0);
                setLayoutContent();
            }
        });

        mDrinkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mDrink.getWebsite()));
                startActivity(intent);
            }
        });

    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                diffx = x2-x1;
                diffy = y2-y1;
            }
            if (x1 < x2 && Math.abs(diffy) < Math.abs(diffx)) {
                        mDrink = Drink.previousDrink(mDrink);
                        setLayoutContent();
            }
            if (x1 > x2 && Math.abs(diffy) < Math.abs(diffx)) {
                        mDrink = Drink.nextDrink(mDrink);
                        setLayoutContent();
            }
            break;
        }
        return false;
    }


    private void setLayoutContent(){
        mDrinkName.setText(mDrink.getPlace());
        mDrinkAddress.setText(mDrink.getAddress());
        mDrinkDescription.setText(mDrink.getInfo());
        Picasso.with(this).load(mDrink.getImage()).into(mDrinkImage);
    }

}
