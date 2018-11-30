package com.assignment.mc.heatbeat.model;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

/**
 * Created by avinash on 30/11/18.
 */

public class MyBarDataSet extends BarDataSet {
    public MyBarDataSet(List<BarEntry> yVals, String label) {
        super(yVals, label);
    }
    @Override
    public int getColor(int index) {
        if(getEntryForIndex(index).getY() < 50) // less than 95 green
            return mColors.get(2);
        else if(getEntryForIndex(index).getY() < 70) // less than 100 orange
            return mColors.get(1);
        else // greater or equal than 100 red
            return mColors.get(0);
    }

}