package com.miguelsanchezp.hackupcproject;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        TextView DailyScore = findViewById(R.id.DailyScore);
        TextView WeeklyScore = findViewById (R.id.WeeklyScore);
        TextView MonthlyScore = findViewById(R.id.MonthlyScore);
        TextView PercentageEco = findViewById (R.id.PercentageEco);
        TextView DistanceEco = findViewById (R.id.DistanceEco);
        TextView TimeEco = findViewById (R.id.TimeEco);
        TextView PercentagePrivate = findViewById(R.id.PercentagePrivate);
        TextView DistancePrivate = findViewById(R.id.DistancePrivate);
        TextView TimePrivate = findViewById (R.id.TimePrivate);
        TextView PercentagePublic = findViewById(R.id.PercentagePublic);
        TextView DistancePublic = findViewById (R.id.DistancePublic);
        TextView TimePublic = findViewById (R.id.TimePublic);
        TextView ValEmission = findViewById(R.id.ValEmission);
        TextView ValAvoided = findViewById(R.id.ValAvoided);

        PendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Pending Dialog must be here", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Toast.makeText(MainActivity.this, "Pending dialog should display here", Toast.LENGTH_SHORT).show();
            }
        });

        PlottingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Plotting dialog should display here", Toast.LENGTH_SHORT).show();
//                Snackbar.make(view, "Plotting DIalog must be here", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
}
