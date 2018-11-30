package com.assignment.mc.heatbeat;

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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    BarChart chart = null;
    EditText textUserName = null;
    Button scan = null;
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
        ArrayList<BarEntry> entries = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            float e1 = rand.nextFloat() *  (max - min) + min;
            float e2 = rand.nextFloat() *  (max - min) + min;
            entries.add(new BarEntry(e1, e2));
        }
        BarDataSet dataset = new BarDataSet(entries, "# of Cells");
        BarData data = new BarData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
    }
}
