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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private double LAT;
    private double FORMERLAT;
    private boolean first = true;
    private double Movement = 0;
    LocationManager locationManager;
    LocationListener locationListener;

//    private FloatingActionButton PendingButton = findViewById(R.id.PendingButton);
//    private FloatingActionButton PlottingButton = findViewById(R.id.PlottingButton);
//    private TextView DailyScore = findViewById(R.id.DailyScore);
//    private TextView WeeklyScore = findViewById (R.id.WeeklyScore);
//    private TextView MonthlyScore = findViewById(R.id.MonthlyScore);
//    private TextView PercentageEco = findViewById (R.id.PercentageEco);
//    private TextView DistanceEco = findViewById (R.id.DistanceEco);
//    private TextView TimeEco = findViewById (R.id.TimeEco);
//    private TextView PercentagePrivate = findViewById(R.id.PercentagePrivate);
//    private TextView DistancePrivate = findViewById(R.id.DistancePrivate);
//    private TextView TimePrivate = findViewById (R.id.TimePrivate);
//    private TextView PercentagePublic = findViewById(R.id.PercentagePublic);
//    private TextView DistancePublic = findViewById (R.id.DistancePublic);
//    private TextView TimePublic = findViewById (R.id.TimePublic);
//    private TextView ValEmission = findViewById(R.id.ValEmission);
//    private TextView ValAvoided = findViewById(R.id.ValAvoided);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "settings dialog should display here", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

         FloatingActionButton PendingButton = findViewById(R.id.PendingButton);
         FloatingActionButton PlottingButton = findViewById(R.id.PlottingButton);
         FloatingActionButton SavingButton = findViewById(R.id.SavingButton);
         FloatingActionButton LoadingButton = findViewById(R.id.LoadingButton);
         final TextView DailyScore = findViewById(R.id.DailyScore);
         final TextView WeeklyScore = findViewById (R.id.WeeklyScore);
         final TextView MonthlyScore = findViewById(R.id.MonthlyScore);
         final TextView PercentageEco = findViewById (R.id.PercentageEco);
         final TextView DistanceEco = findViewById (R.id.DistanceEco);
         final TextView TimeEco = findViewById (R.id.TimeEco);
         final TextView PercentagePrivate = findViewById(R.id.PercentagePrivate);
         final TextView DistancePrivate = findViewById(R.id.DistancePrivate);
         final TextView TimePrivate = findViewById (R.id.TimePrivate);
         final TextView PercentagePublic = findViewById(R.id.PercentagePublic);
         final TextView DistancePublic = findViewById (R.id.DistancePublic);
         final TextView TimePublic = findViewById (R.id.TimePublic);
         final TextView ValEmission = findViewById(R.id.ValEmission);
         final TextView ValAvoided = findViewById(R.id.ValAvoided);

        PendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Pending dialog should display here", Toast.LENGTH_SHORT).show();
                startStuff ();
            }
        });

        PlottingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Plotting dialog should display here", Toast.LENGTH_SHORT).show();
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
                                WriteData.exportMovement(0);
                            }else {
                                FORMERLAT = LAT;
                                LAT = location.getLatitude();
                                Movement += calculateMovement(LAT, FORMERLAT);
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
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
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

    private void saveData () {
        Data data = new Data();
        data.setEcoFriendlyDistance(10.0);
        data.setPrivateDistance(10.0);
        data.setPublicDistance(10.0);
        data.setEcoFriendlySecs(4802);
        data.setPrivateSecs(1054);
        data.setPublicSecs(2514);
        data.setOthers();
        WriteData.WriteIntoFile(data);
    }

    private void fillLayout (TextView PercentageEco, TextView DistanceEco, TextView TimeEco, TextView PercentagePrivate,
                             TextView DistancePrivate, TextView TimePrivate, TextView PercentagePublic, TextView DistancePublic,
                             TextView TimePublic, TextView ValEmission, TextView ValAvoided, TextView DailyScore,
                             TextView WeeklyScore, TextView MonthlyScore) {
        Data data = ReadData.ReadFromFile();
        if (data != null) {
            PercentageEco.setText(data.getEcoFriendlyPercentage() + "%");
            DistanceEco.setText(data.getEcoFriendlyDistance()+"km");
            TimeEco.setText(formatSeconds(data.getEcoFriendlySecs()));
            PercentagePrivate.setText(data.getPrivatePercentage() + "%");
            DistancePrivate.setText(data.getPrivateDistance() + "km");
            TimePrivate.setText(formatSeconds(data.getPrivateSecs()));
            PercentagePublic.setText(data.getPublicPercentage()+"%");
            DistancePublic.setText(data.getPublicDistance() + "km");
            TimePublic.setText(formatSeconds(data.getPublicSecs()));
            ValEmission.setText(data.getEmittedCO2()+"kg");
            ValAvoided.setText(data.getAvoidedCO2()+"kg");
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

    private double calculateMovement (double latitude, double formerlatitude) {
        double kmX = 111.32*(latitude-formerlatitude);
        double kmY = (double)40075*Math.cos(latitude-formerlatitude)/360;
        return Math.sqrt(Math.pow(kmX, 2) + Math.pow(kmY, 2));
    }
}
