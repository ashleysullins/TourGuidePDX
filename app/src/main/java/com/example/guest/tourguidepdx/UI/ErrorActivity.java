package com.example.guest.tourguidepdx.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.guest.tourguidepdx.R;

/**
 * Created by Guest on 11/11/15.
 */
public class ErrorActivity extends PlaceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        mName.setText("Network Unavailable");
        mAddress.setText("Try again?");
        mAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ErrorActivity.this, LetsGoActivity.class);
                startActivity(intent);
            }
        });
    }
}
