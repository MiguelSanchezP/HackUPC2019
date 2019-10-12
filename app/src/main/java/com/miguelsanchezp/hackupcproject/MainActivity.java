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

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private double LONG;
    private double LAT;

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
         TextView DailyScore = findViewById(R.id.DailyScore);
         TextView WeeklyScore = findViewById (R.id.WeeklyScore);
         TextView MonthlyScore = findViewById(R.id.MonthlyScore);
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
                        PercentagePublic, DistancePublic, TimePublic, ValEmission, ValAvoided);
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

/*    private void getLocation ()  {
//        LocationManager locationManager;
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        locationManager.getBestProvider(criteria, true);
//            String provider = locationManager.getProviders(true).get(0);
                Log.d(TAG, "getLocation: this is not null :)!!!");
//                try {

//                    Location location = locationManager.getLastKnownLocation(provider);
                    LocationListener locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            setLatitude(location.getLatitude());
                            setLongitude(location.getLongitude());
                            Log.d(TAG, "onLocationChanged: " + location.getLongitude());
                            Log.d(TAG, "onLocationChanged: " + location.getLatitude());
                            
                        }

                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {

                        }

                        @Override
                        public void onProviderEnabled(String s) {

                        }

                        @Override
                        public void onProviderDisabled(String s) {

                        }
                    };
                    try {
                        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        Log.d(TAG, "getLocation: inside!!!");
                    } catch (SecurityException e) {
                        Log.e(TAG, "getLocation: " + e.getMessage());
                    }
                    Toast.makeText(this, LONG + " , " + LAT, Toast.LENGTH_SHORT).show();
//                } catch (SecurityException e) {
//                    Log.e(TAG, "getLocation: there is a security exception");
//                }
            }
//        }
//    }

    private void setLongitude (double LONG) {
        this.LONG = LONG;
    }

    private void setLatitude (double LAT) {
        this.LAT = LAT;
    }*/

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
                             TextView TimePublic, TextView ValEmission, TextView ValAvoided) {
        //null pointer, make the check going to breakfast first :)
        Data data = ReadData.ReadFromFile();
        if (data != null) {
            PercentageEco.setText(String.valueOf(data.getEcoFriendlyPercentage()));
            DistanceEco.setText(String.valueOf(data.getEcoFriendlyDistance()));
            TimeEco.setText(String.valueOf(data.getEcoFriendlySecs()));
            PercentagePrivate.setText(String.valueOf(data.getPrivatePercentage()));
            DistancePrivate.setText(String.valueOf(data.getPrivateDistance()));
            TimePrivate.setText(String.valueOf(data.getPrivateSecs()));
            PercentagePublic.setText(String.valueOf(data.getPublicPercentage()));
            DistancePublic.setText(String.valueOf(data.getPublicDistance()));
            TimePublic.setText(String.valueOf(data.getPublicSecs()));
            ValEmission.setText(String.valueOf(data.getEmittedCO2()));
            ValAvoided.setText(String.valueOf(data.getAvoidedCO2()));
        }else{
            Toast.makeText(this, "The data import was a null one", Toast.LENGTH_LONG).show();
        }
    }
}
