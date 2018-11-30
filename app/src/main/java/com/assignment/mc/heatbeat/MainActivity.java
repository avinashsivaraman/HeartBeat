package com.assignment.mc.heatbeat;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.assignment.mc.heatbeat.model.MyBarDataSet;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    BarChart chart = null;
    EditText textUserName = null;
    Button scan = null;
    Context context = MainActivity.this;

    Float[] demoData =  {81.600f, 86.880f, 85.440f, 91.680f, 90.240f, 87.840f, 83.520f, 83.040f, 84.480f, 81.600f, 79.200f, 78.240f, 79.200f, 96f, 96.480f, 81.120f, 81.600f, 80.640f, 89.280f, 88.800f, 80.640f, 84.480f, 86.400f, 83.520f, 78.720f, 80.160f, 85.920f, 96.960f, 84.960f, 148.32f, 69.120f, 71.520f, 52.320f, 12.480f, 78.240f, 73.440f, 72.480f};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chart = (BarChart) findViewById(R.id.barchart);
        textUserName = (EditText) findViewById(R.id.edit_user);
        Button go = (Button) findViewById(R.id.button_go);
        scan = (Button) findViewById(R.id.button_scan);
        scan.setVisibility(View.INVISIBLE);
        chart.setVisibility(View.INVISIBLE);
        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                scan.setVisibility(View.VISIBLE);
                setChartData(textUserName.getText().toString());
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                chart.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Scanning the Heart Beat", Toast.LENGTH_LONG).show();
                setChartData(textUserName.getText().toString());
            }
        });

    }

    void setChartData(String Name) {
        chart.setVisibility(View.VISIBLE);
        Log.d("Main_Activity", Name);
        Float max = 100.f;
        Float min = 20.0f;
        ArrayList<BarEntry> yVals = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < demoData.length; i++) {
            yVals.add(new BarEntry(i, demoData[i]));
        }

        MyBarDataSet set = new MyBarDataSet(yVals, "");
        set.setColors(ContextCompat.getColor(context, R.color.green),
                ContextCompat.getColor(context, R.color.orange),
                ContextCompat.getColor(context, R.color.red));

        BarData data = new BarData(set);
        chart.setData(data);
    }
}
