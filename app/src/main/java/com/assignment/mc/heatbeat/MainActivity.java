package com.assignment.mc.heatbeat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.mc.heatbeat.model.MyBarDataSet;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private BarChart chart = null;
    private EditText textUserName = null;
    private Button scan = null;
    private Context context = MainActivity.this;
    private ImageView mImageView;
    private Boolean isBradycardia = Boolean.FALSE;
    private TextView textViewDisplay = null;

    List<Float> demoData = new ArrayList<>();
    Map<String, String> fileName = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileName.put("Avinash", "data1");
        fileName.put("Jaden", "data2");
        fileName.put("Melvin", "data3");
        fileName.put("Rishabh", "data4");
        chart = (BarChart) findViewById(R.id.barchart);
        textUserName = (EditText) findViewById(R.id.edit_user);
        Button go = (Button) findViewById(R.id.button_go);
        scan = (Button) findViewById(R.id.button_scan);
        mImageView = (ImageView) findViewById(R.id.image1);
        textViewDisplay = (TextView) findViewById(R.id.text_result);

//       Make all the charts Invisible
        scan.setVisibility(View.GONE);
        chart.setVisibility(View.GONE);
        mImageView.setVisibility(View.GONE);
        textViewDisplay.setVisibility(View.GONE);
//
        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                chart.setVisibility(View.GONE);
                textViewDisplay.setVisibility(View.GONE);
                showImage(fileName.get(textUserName.getText().toString()));
                scan.setVisibility(View.VISIBLE);
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                mImageView.setVisibility(View.GONE);
                scan.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Scanning the Heart Beat", Toast.LENGTH_SHORT).show();
                try {
                    getData(textUserName.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setChartData();
                if (isBradycardia) {
                    textViewDisplay.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void showImage(String s) {
//        mImageView.setImageBitmap(BitmapFactory.decodeFile(s+".png"));
        try {
            // get input stream
            InputStream ims = getAssets().open(s + ".png");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            mImageView.setImageDrawable(d);
            ims.close();
            mImageView.setVisibility(View.VISIBLE);
        } catch (IOException ex) {
            return;
        }
        mImageView.setVisibility(View.VISIBLE);
    }

    void getData(String name) throws IOException {
        String filePath = fileName.get(name);
        demoData.clear();
        LoadFile(filePath);
    }

    void setChartData() {
        isBradycardia = Boolean.FALSE;
        chart.setVisibility(View.VISIBLE);
        ArrayList<BarEntry> yVals = new ArrayList<>();
        for (int i = 0; i < demoData.size(); i++) {
            Float current = demoData.get(i);
            if (current < 50.0f) {
                isBradycardia = Boolean.TRUE;
            }
            yVals.add(new BarEntry(i, current));

        }

        MyBarDataSet set = new MyBarDataSet(yVals, "");
        set.setColors(ContextCompat.getColor(context, R.color.green),
                ContextCompat.getColor(context, R.color.orange),
                ContextCompat.getColor(context, R.color.red));

        BarData data = new BarData(set);
        chart.setData(data);
    }

    public void LoadFile(String fileName) throws IOException {
        Log.d("MainActivity", fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName + ".txt")));
        String line = reader.readLine();
        while (line != null) {
            demoData.add(Float.valueOf(line));
            line = reader.readLine();
        }
    }
    //return the output stream as a String
}
