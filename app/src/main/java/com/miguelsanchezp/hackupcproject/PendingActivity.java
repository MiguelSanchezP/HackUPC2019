package com.miguelsanchezp.hackupcproject;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PendingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_activity);
        final RadioButton ItineraryEcoFriendly = findViewById(R.id.ItineraryEcoFriendly);
        final RadioButton ItineraryPrivate = findViewById (R.id.ItineraryPrivate);
        final RadioButton ItineraryPublic = findViewById (R.id.ItineraryPublic);
        ArrayList<RadioButton> buttons = new ArrayList<>();
        buttons.add(ItineraryEcoFriendly);
        buttons.add(ItineraryPrivate);
        buttons.add(ItineraryPublic);
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int id = compoundButton.getId();
                switch (id) {
                    case R.id.ItineraryEcoFriendly:
                        ItineraryEcoFriendly.setChecked(true);
                        ItineraryPrivate.setChecked(false);
                        ItineraryPublic.setChecked(false);
                        break;
                    case R.id.ItineraryPrivate:
                        ItineraryEcoFriendly.setChecked(false);
                        ItineraryPrivate.setChecked(true);
                        ItineraryPublic.setChecked(false);
                        break;
                    case R.id.ItineraryPublic:
                        ItineraryEcoFriendly.setChecked(false);
                        ItineraryPrivate.setChecked(false);
                        ItineraryPublic.setChecked(true);
                        break;
                }
            }
        };

        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.ItineraryEcoFriendly:
                        ItineraryEcoFriendly.setChecked(true);
                        ItineraryPrivate.setChecked(false);
                        ItineraryPublic.setChecked(false);
                        break;
                    case R.id.ItineraryPrivate:
                        ItineraryEcoFriendly.setChecked(false);
                        ItineraryPrivate.setChecked(true);
                        ItineraryPublic.setChecked(false);
                        break;
                    case R.id.ItineraryPublic:
                        ItineraryEcoFriendly.setChecked(false);
                        ItineraryPrivate.setChecked(false);
                        ItineraryPublic.setChecked(true);
                        break;
                }
            }
        };
//        ItineraryEcoFriendly.setOnCheckedChangeListener(listener);
//        ItineraryPrivate.setOnCheckedChangeListener(listener);
//        ItineraryPublic.setOnCheckedChangeListener(listener);
        ItineraryEcoFriendly.setOnClickListener(listener1);
        ItineraryPrivate.setOnClickListener(listener1);
        ItineraryPublic.setOnClickListener(listener1);
//        ItineraryEcoFriendly.setChecked(true);
    }
}
