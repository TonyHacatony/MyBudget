package com.example.noname.mybudget.mainMenuFragments;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noname.mybudget.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import com.example.noname.mybudget.Data.DataBaseHelper;



public class dataFragment extends Fragment {
    PieChart pieChart;
    DataBaseHelper myDb;
    TextView textType,textValue;

    float food = 1;
    float big = 1;
    float medicine = 1;
    float other = 1;
    float every = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_data, container, false);

        pieChart  = view.findViewById(R.id.idPieChart);
        myDb = new DataBaseHelper(getActivity());

        textType = view.findViewById(R.id.titleType);
        textValue = view.findViewById(R.id.titleValue);


        //pie chart settings
        pieChart.setRotationEnabled(false);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Money");
        pieChart.setCenterTextSize(20);
        pieChart.setDrawEntryLabels(true);

        //pie chart data

        addDataSet();


        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });

        return view;
    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();

        yEntrys.add(new PieEntry(food,"Food"));
        yEntrys.add(new PieEntry(big,"BigPurchase"));
        yEntrys.add(new PieEntry(medicine,"Medicine"));
        yEntrys.add(new PieEntry(every,"EveryMonth"));
        yEntrys.add(new PieEntry(other,"Other"));

            //set pieDataSet
        PieDataSet dataSet = new PieDataSet(yEntrys,"Numbers");
        dataSet.setSliceSpace(2);
        dataSet.setValueTextSize(12);
        dataSet.notifyDataSetChanged();

        //add legend to data
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setEnabled(true);

        //set colors
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.CYAN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GRAY);
        colors.add(Color.MAGENTA);
        dataSet.setColors(colors);

        //set pieData
        PieData data = new PieData(dataSet);
        data.setValueTextSize(5f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

    }

    public void addYData() {

        Cursor res = myDb.getSumOfType();
        if (res.moveToFirst()) {
            do {
                if(res.getString(0).equals("Food"))food = ( Float.valueOf(res.getString(1)));
                if(res.getString(0).equals("BigPurchase"))big = ( Float.valueOf(res.getString(1)));
                if(res.getString(0).equals("Medicine"))medicine = ( Float.valueOf(res.getString(1)));
                if(res.getString(0).equals("EveryMonth"))every = ( Float.valueOf(res.getString(1)));
                if(res.getString(0).equals("Other"))other = ( Float.valueOf(res.getString(1)));
            }
            while (res.moveToNext());
        }
        res.close();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            addYData();
            addDataSet();
            pieChart.animateX(2000);
        }
    }

}


