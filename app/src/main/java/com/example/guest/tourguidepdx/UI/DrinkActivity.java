package com.example.guest.tourguidepdx.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.tourguidepdx.Models.Drink;
import com.example.guest.tourguidepdx.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrinkActivity extends AppCompatActivity {

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
                List<Drink> mAllDrinks = Drink.getDrinks();
                mDrink = mAllDrinks.get(0);
                setLayoutContent();
            }
        });


    }

    private void setLayoutContent() {
        mDrinkName.setText(mDrink.getPlace());
        mDrinkAddress.setText(mDrink.getAddress());
        mDrinkDescription.setText(mDrink.getInfo());
    }


}
