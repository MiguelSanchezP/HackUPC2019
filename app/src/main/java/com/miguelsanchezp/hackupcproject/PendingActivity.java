package com.miguelsanchezp.hackupcproject;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

import static com.miguelsanchezp.hackupcproject.MainActivity.getPendingActivities;

public class PendingActivity extends AppCompatActivity {

//    public static ArrayList<PendingItem> pendingActivities;
    private int position=1;
    private static final String TAG = "PendingActivity";
    private boolean cancel = false;
    private boolean next = false;

//    public PendingActivity (ArrayList<PendingItem> pendingActivities) {
//        this.pendingActivities = pendingActivities;
//    }

    public PendingActivity () {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_activity);
        final ArrayList<PendingItem> pendingActivities2 = getPendingActivities();
        Log.d(TAG, "onCreate: " + pendingActivities2.size());
        final TextView ItineraryDistance = findViewById(R.id.ItineraryDistance);
        final TextView ItineraryStartTime = findViewById(R.id.InitialTime);
        final TextView ItineraryFinalTime = findViewById(R.id.FinalTime);
        final TextView Title = findViewById(R.id.ItineraryName);
        final RadioButton ItineraryEcoFriendly = findViewById(R.id.ItineraryEcoFriendly);
        final RadioButton ItineraryPrivate = findViewById (R.id.ItineraryPrivate);
        final RadioButton ItineraryPublic = findViewById (R.id.ItineraryPublic);
        ItineraryEcoFriendly.setSelected(true);
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
        ItineraryEcoFriendly.setOnClickListener(listener1);
        ItineraryPrivate.setOnClickListener(listener1);
        ItineraryPublic.setOnClickListener(listener1);


        Button ItineraryButton = findViewById(R.id.ItineraryNext);
//        prepare(pendingActivities2, Title, ItineraryDistance, ItineraryStartTime, ItineraryFinalTime, ItineraryEcoFriendly, ItineraryPrivate, ItineraryPublic);

        ItineraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ItineraryEcoFriendly.isSelected()) {
//                    MainActivity.allData.setEcoFriendlySecs(MainActivity.allData.getEcoFriendlySecs()+(Integer.parseInt(ItineraryFinalTime.getText().toString())-Integer.parseInt(ItineraryStartTime.getText().toString())));
//                    MainActivity.allData.setEcoFriendlyDistance(MainActivity.allData.getEcoFriendlyDistance()+Integer.parseInt(ItineraryDistance.getText().toString()));
                    MainActivity.allData.setEcoFriendlySecs(MainActivity.allData.getEcoFriendlySecs()+(int)(Long.parseLong(pendingActivities2.get(position-1).getFinalTime())-Long.parseLong(pendingActivities2.get(position).getInitialTime())));
//                    MainActivity.allData.setEcoFriendlyDistance(MainActivity.allData.getEcoFriendlyDistance()+Integer.parseInt(pendingActivities2.get(position-1).getDistance()));
                }
                if (ItineraryPrivate.isSelected()) {
//                    MainActivity.allData.setPrivateSecs(MainActivity.allData.getPrivateSecs()+Integer.parseInt(ItineraryFinalTime.getText().toString())-Integer.parseInt(ItineraryStartTime.getText().toString()));
//                    MainActivity.allData.setPrivateDistance(MainActivity.allData.getPrivateDistance()+Integer.parseInt(ItineraryDistance.getText().toString()));
                    MainActivity.allData.setPrivateSecs(MainActivity.allData.getPrivateSecs()+(int)(Long.parseLong(pendingActivities2.get(position-1).getFinalTime())-Long.parseLong(pendingActivities2.get(position).getInitialTime())));
//                    MainActivity.allData.setPrivateDistance(MainActivity.allData.getPrivateDistance()+Integer.parseInt(pendingActivities2.get(position-1).getDistance()));

                }
                if (ItineraryPublic.isSelected()) {
//                    MainActivity.allData.setPublicSecs(MainActivity.allData.getPublicSecs() + Integer.parseInt(ItineraryFinalTime.getText().toString()) - Integer.parseInt(ItineraryStartTime.getText().toString()));
//                    MainActivity.allData.setPublicDistance(MainActivity.allData.getPublicDistance() + Integer.parseInt(ItineraryDistance.getText().toString()));
                    MainActivity.allData.setPublicSecs(MainActivity.allData.getPublicSecs()+(int)(Long.parseLong(pendingActivities2.get(position-1).getFinalTime())-Long.parseLong(pendingActivities2.get(position).getInitialTime())));
                }
                prepare(pendingActivities2, Title, ItineraryDistance, ItineraryStartTime, ItineraryFinalTime, ItineraryEcoFriendly, ItineraryPrivate, ItineraryPublic, position);
                position++;
//                pendingActivities2.remove(0);
//                if (pendingActivities2.size() == 0) {
//                    Toast.makeText(PendingActivity.this, "There are no pending itineraries", Toast.LENGTH_LONG).show();
//                }
//                for (PendingItem i : pendingActivities2) {
//                    pendingActivities2.set(pendingActivities2.indexOf(i)-1, i);
//                }
//                MainActivity.saveData();
//                new MainActivity().fillEverything();
//                prepare(position, Title, ItineraryDistance, ItineraryStartTime, ItineraryFinalTime, ItineraryEcoFriendly, ItineraryPrivate, ItineraryPublic);
            }
        });

        Button cancelButton = findViewById(R.id.ItineraryDone);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel = true;
            }
        });

        prepare(pendingActivities2, Title, ItineraryDistance, ItineraryStartTime, ItineraryFinalTime, ItineraryEcoFriendly, ItineraryPrivate, ItineraryPublic, position);
    }


    public void prepare (ArrayList<PendingItem> pendingActivities, TextView title, TextView distance, TextView initialTime, TextView finalTime, RadioButton ItineraryEcoFriendly, RadioButton ItineraryPrivate, RadioButton ItineraryPublic, int  round){
        if (pendingActivities.isEmpty()) {
            Toast.makeText(this, "There are no pending activities", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
                next = false;
                String Title = "Itinerary #" + round;
                title.setText(Title);
//        if (pendingActivities != null) {
//            if (pendingActivities.size() > p) {
                initialTime.setText(pendingActivities.get(round - 1).getInitialTime());
                finalTime.setText(pendingActivities.get(round - 1).getFinalTime());
                long time = (Long.parseLong(pendingActivities.get(round - 1).getFinalTime()) - Long.parseLong(pendingActivities.get(round - 1).getInitialTime()) / 1000);
                if (ItineraryEcoFriendly.isSelected()) {
                    distance.setText(String.valueOf(time * 2));
                    MainActivity.allData.setEcoFriendlyDistance(MainActivity.allData.getEcoFriendlyDistance()+Double.parseDouble(distance.getText().toString()));
                }
                if (ItineraryPublic.isSelected()) {
                    distance.setText(String.valueOf(time * 18));
                    MainActivity.allData.setPrivateDistance(MainActivity.allData.getPrivateDistance()+Double.parseDouble(distance.getText().toString()));
                }
                if (ItineraryPrivate.isSelected()) {
                    distance.setText(String.valueOf(time * 25));
                    MainActivity.allData.setPublicDistance(MainActivity.allData.getPublicDistance()+Double.parseDouble(distance.getText().toString()));
                }
            }
            }



//    public static void addPendingItem (PendingItem pendingItem) {
//        if (pendingActivities != null) {
//            Log.d(TAG, "addPendingItem: added a pending item :)");
//            pendingActivities.add(pendingItem);
//        }
//    }
//
//    public static int getPendingSize () {
//        return pendingActivities.size();
//    }

