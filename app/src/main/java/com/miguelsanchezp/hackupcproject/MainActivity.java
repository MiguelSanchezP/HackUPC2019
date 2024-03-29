package com.miguelsanchezp.hackupcproject;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final Data allData = new Data (0,0,0,0,0,0);
    public static ArrayList<PendingItem> pendingActivities = new ArrayList<>();
    private static final String TAG = "MainActivity";
    private double LAT;
    private double LONG;
    private double FORMERLAT;
    private double FORMERLONG;
    private boolean first = true;
    private double Movement = 0;
    LocationManager locationManager;
    LocationListener locationListener;
    private double distancePrev = 0;
    private double distance = 0;
    private boolean running = false;
    long initialMillis;
    long finalMillis;
    private static PendingActivity pendingActivity = new PendingActivity();

//    FloatingActionButton PendingButton = findViewById(R.id.PendingButton);
//    FloatingActionButton PlottingButton = findViewById(R.id.PlottingButton);
//    FloatingActionButton SavingButton = findViewById(R.id.SavingButton);
//    FloatingActionButton LoadingButton = findViewById(R.id.LoadingButton);
//    FloatingActionButton ButtonStartend = findViewById(R.id.ButtonStartend);
//    final TextView DailyScore = findViewById(R.id.DailyScore);
//    final TextView WeeklyScore = findViewById (R.id.WeeklyScore);
//    final TextView MonthlyScore = findViewById(R.id.MonthlyScore);
//    final TextView PercentageEco = findViewById (R.id.PercentageEco);
//    final TextView DistanceEco = findViewById (R.id.DistanceEco);
//    final TextView TimeEco = findViewById (R.id.TimeEco);
//    final TextView PercentagePrivate = findViewById(R.id.PercentagePrivate);
//    final TextView DistancePrivate = findViewById(R.id.DistancePrivate);
//    final TextView TimePrivate = findViewById (R.id.TimePrivate);
//    final TextView PercentagePublic = findViewById(R.id.PercentagePublic);
//    final TextView DistancePublic = findViewById (R.id.DistancePublic);
//    final TextView TimePublic = findViewById (R.id.TimePublic);
//    final TextView ValEmission = findViewById(R.id.ValEmission);
//    final TextView ValAvoided = findViewById(R.id.ValAvoided);

    FloatingActionButton PendingButton;
    FloatingActionButton PlottingButton;
    FloatingActionButton SavingButton;
    FloatingActionButton LoadingButton;
    FloatingActionButton ButtonStartend;
     TextView DailyScore;
     TextView WeeklyScore;
     TextView MonthlyScore;
     TextView PercentageEco;
     TextView DistanceEco;
     TextView TimeEco;
     TextView PercentagePrivate;
     TextView DistancePrivate;
     TextView TimePrivate;
     TextView PercentagePublic;
     TextView DistancePublic;
     TextView TimePublic;
     TextView ValEmission;
     TextView ValAvoided;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        PendingButton = findViewById(R.id.PendingButton);
         PlottingButton = findViewById(R.id.PlottingButton);
         SavingButton = findViewById(R.id.SavingButton);
         LoadingButton = findViewById(R.id.LoadingButton);
         ButtonStartend = findViewById(R.id.ButtonStartend);
         DailyScore = findViewById(R.id.DailyScore);
          WeeklyScore = findViewById (R.id.WeeklyScore);
         MonthlyScore = findViewById(R.id.MonthlyScore);
          PercentageEco = findViewById (R.id.PercentageEco);
         DistanceEco = findViewById (R.id.DistanceEco);
         TimeEco = findViewById (R.id.TimeEco);
         PercentagePrivate = findViewById(R.id.PercentagePrivate);
         DistancePrivate = findViewById(R.id.DistancePrivate);
         TimePrivate = findViewById (R.id.TimePrivate);
         PercentagePublic = findViewById(R.id.PercentagePublic);
          DistancePublic = findViewById (R.id.DistancePublic);
          TimePublic = findViewById (R.id.TimePublic);
         ValEmission = findViewById(R.id.ValEmission);
          ValAvoided = findViewById(R.id.ValAvoided);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "settings dialog should display here", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        PendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Pending dialog should display here", Toast.LENGTH_SHORT).show();
                startStuff();
            }
        });

        PlottingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Plotting dialog should display here", Toast.LENGTH_SHORT).show();
                getLocation();
            }
        });

        SavingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Files are being saved", Toast.LENGTH_LONG).show();
                saveData();
            }
        });

        LoadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Importing data", Toast.LENGTH_LONG).show();
                fillLayout(PercentageEco, DistanceEco, TimeEco, PercentagePrivate, DistancePrivate, TimePrivate,
                        PercentagePublic, DistancePublic, TimePublic, ValEmission, ValAvoided, DailyScore, WeeklyScore,
                        MonthlyScore);
            }
        });

        ButtonStartend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!running) {
                    running = true;
                    initialMillis = System.currentTimeMillis();
                    Toast.makeText(MainActivity.this, "Activity started", Toast.LENGTH_SHORT).show();
                }else{
                    running = false;
                    finalMillis = System.currentTimeMillis();
                    Toast.makeText(MainActivity.this, "Activity ended", Toast.LENGTH_SHORT).show();
                    PendingItem pendingItem = new PendingItem(String.valueOf(initialMillis), String.valueOf(finalMillis));
                    pendingActivities.add(pendingItem);
                    Toast.makeText(MainActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
//                    pendingActivity.addPendingItem(pendingItem);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startStuff () {

        Intent intent = new Intent (this, PendingActivity.class);
        startActivity(intent);
    }

    public static Context getContext () {
        return new MainActivity();
    }

    private void getLocation ()  {
//        LocationManager locationManager;
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(criteria, true);
//        Log.d(TAG, "getLocation: !!!!" + provider);
//            String provider = locationManager.getProviders(true).get(0);
//                Log.d(TAG, "getLocation: this is not null :)!!!");
//                try {

//                    Location location = locationManager.getLastKnownLocation(provider);
                    locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            if (first) {
                                first = false;
                                FORMERLAT = location.getLatitude();
                                FORMERLONG = location.getLongitude();
                                WriteData.exportMovement(0);
                            }else {
                                FORMERLAT = LAT;
                                FORMERLONG = LONG;
                                LONG = location.getLongitude();
                                LAT = location.getLatitude();
                                Movement += calculateMovement(LAT, FORMERLAT, LONG, FORMERLONG);
                                WriteData.exportMovement(Movement);
                            }
                        }

                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {
                            Log.d(TAG, "onStatusChanged: inside onStatusChanged");
                        }

                        @Override
                        public void onProviderEnabled(String s) {

                        }

                        @Override
                        public void onProviderDisabled(String s) {

                        }
                    };
                    try {
//                        locationManager.requestLocationUpdates(provider, 5000, 30, locationListener);
//                        Log.d(TAG, "getLocation: inside!!!");
//                        locationManager.requestLocationUpdates(provider, 0, 0, locationListener);
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50, 1, locationListener);
//                        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    } catch (SecurityException e) {
//                        Log.e(TAG, "getLocation: " + e.getMessage());
                    }
//                    Toast.makeText(this, LONG + " , " + LAT, Toast.LENGTH_SHORT).show();
//                } catch (SecurityException e) {
//                    Log.e(TAG, "getLocation: there is a security exception");
//                }
            }
//        }
//    }

    public static void saveData () {
//        Data data = new Data();
//        data.setEcoFriendlyDistance(10.0);
//        data.setPrivateDistance(10.0);
//        data.setPublicDistance(10.0);
//        data.setEcoFriendlySecs(4802);
//        data.setPrivateSecs(1054);
//        data.setPublicSecs(2514);
//        data.setOthers();

        WriteData.WriteIntoFile(allData);
    }

    public void fillEverything () {
        fillLayout(PercentageEco, DistanceEco, TimeEco, PercentagePrivate, DistancePrivate, TimePrivate, PercentagePublic, DistancePublic, TimePublic, ValEmission, ValAvoided, DailyScore, WeeklyScore, MonthlyScore);
    }

    private void fillLayout (TextView PercentageEco, TextView DistanceEco, TextView TimeEco, TextView PercentagePrivate,
                             TextView DistancePrivate, TextView TimePrivate, TextView PercentagePublic, TextView DistancePublic,
                             TextView TimePublic, TextView ValEmission, TextView ValAvoided, TextView DailyScore,
                             TextView WeeklyScore, TextView MonthlyScore) {
        Data data = ReadData.ReadFromFile();
        if (data != null) {
            PercentageEco.setText(String.valueOf(data.getEcoFriendlyPercentage()));
            DistanceEco.setText(String.valueOf(data.getEcoFriendlyDistance()));
            TimeEco.setText(formatSeconds(data.getEcoFriendlySecs()));
            PercentagePrivate.setText(String.valueOf(data.getPrivatePercentage()));
            DistancePrivate.setText(String.valueOf(data.getPrivateDistance()));
            TimePrivate.setText(formatSeconds(data.getPrivateSecs()));
            PercentagePublic.setText(String.valueOf(data.getPublicPercentage()));
            DistancePublic.setText(String.valueOf(data.getPublicDistance()));
            TimePublic.setText(formatSeconds(data.getPublicSecs()));
            ValEmission.setText(String.valueOf(data.getEmittedCO2()));
            ValAvoided.setText(String.valueOf(data.getAvoidedCO2()));
            DailyScore.setText(String.valueOf(data.getDailyScore()));
            WeeklyScore.setText(String.valueOf(data.getWeeklyScore()));
            MonthlyScore.setText(String.valueOf(data.getMonthlyScore()));
        }else{
            Toast.makeText(this, "The data import was a null one", Toast.LENGTH_LONG).show();
        }
    }

    private String formatSeconds (int seconds) {
        StringBuilder sb = new StringBuilder();
        int hours = seconds/3600;
        if (hours<10) {
            sb.append("0");
            sb.append(hours);
        }else{
            sb.append(hours);
        }
        sb.append(":");
        int minutes = (seconds%3600)/60;
        if (minutes < 10) {
            sb.append("0");
            sb.append(minutes);
        }else{
            sb.append(minutes);
        }
        sb.append(":");
        int secs = (seconds%3600)%60;
        if (secs < 10) {
            sb.append("0");
            sb.append (secs);
        }else{
            sb.append(secs);
        }
        return sb.toString();
    }

    private double calculateMovement (double latitude, double formerlatitude, double longitude, double formerlongitude) {
        distancePrev = distance;
        double radLat = latitude*Math.PI/180;
        double radFormerLat = formerlatitude*Math.PI/180;
        double radLong = longitude*Math.PI/180;
        double radFormerLong = formerlongitude*Math.PI/180;
        distance = 2*6370*Math.asin(Math.sqrt(Math.pow(Math.sin((radLat-radFormerLat)/2), 2) + Math.cos(radFormerLat)*Math.cos(radLat)*Math.pow(Math.sin((radLong-radFormerLong)/2),2)));


//        double kmX = 111.32*(latitude-formerlatitude);
//        Toast.makeText(this, String.valueOf(latitude-formerlatitude), Toast.LENGTH_SHORT).show();
//        double kmY = (double)40075*Math.cos(latitude-formerlatitude)/360;
//        return Math.sqrt(Math.pow(kmX, 2) + Math.pow(kmY, 2));
        Toast.makeText(this, String.valueOf(distance*1000), Toast.LENGTH_SHORT).show();
        return distance*1000000;
    }

    public static ArrayList<PendingItem> getPendingActivities () {
        return pendingActivities;
    }
}
