package com.example.noname.mybudget.mainMenuFragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noname.mybudget.Data.DataBaseHelper;
import com.example.noname.mybudget.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Float.parseFloat;
import static java.lang.Float.valueOf;


public class mainFragment extends Fragment {

    String checkedSum="";
    FloatingActionButton flBtnP,flBtnM;
    DataBaseHelper myDb;
    float profit=0,balance=0,outgo=0;
    boolean mark;
    TextView textTitle,textProfit,textBalance,textOutgo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_main, container, false);

        textTitle = view.findViewById(R.id.textTitle);
        textProfit = view.findViewById(R.id.textProfit);
        textBalance = view.findViewById(R.id.textBalance);
        textOutgo = view.findViewById(R.id.textOutgo);

        myDb = new DataBaseHelper(getActivity());
        flBtnP = view.findViewById(R.id.floatBtnPlus);
        flBtnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark = true;
                showD().create();

            }
        });
        flBtnM = view.findViewById(R.id.floatBtnMinus);
        flBtnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark = false;
                showD().create();
            }
        });

        if(myDb!=null) addData();
        return view;
    }

    private Dialog showD(){


        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        //first(-) spinner
        final Spinner editTextMinus = new Spinner(getActivity());
        String [] TypesMinus={getString(R.string.FOOD),getString(R.string.BigBuy),getString(R.string.EveryMonth),
                getString(R.string.Medicine),getString(R.string.Other)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,TypesMinus);

        //second(+) spinner
        final Spinner editTextPlus = new Spinner(getActivity());
        String [] TypesPlus={"Salary","Spin-off"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,TypesPlus);

        if(mark==true) {
            editTextPlus.setAdapter(adapter1);
            layout.addView(editTextPlus);
        } else {
        editTextMinus.setAdapter(adapter);
        layout.addView(editTextMinus);}


        final EditText inputSum = new EditText(getActivity());
        inputSum.setHint("How much");
        layout.addView(inputSum);

        builder.setTitle("How much u got");
        builder.setView(layout);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkText(inputSum);
                ///set data
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String currentData = sdf.format(new Date());

                if(mark == true) {
                    myDb.insertData(editTextPlus.getSelectedItem().toString(),checkedSum,"+",currentData);
                    addData();
                }else{
                    myDb.insertData(editTextMinus.getSelectedItem().toString(),checkedSum,"-",currentData);
                    addData();
                }
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.show();
    }

    private void checkText(EditText text) {
        String ourText = text.getText().toString();
        String resultText="";
        for(int i=0;i<text.length();i++){
            if(ourText.charAt(i)!=',') resultText += ourText.charAt(i);
            else resultText+=".";
        }
        checkedSum = resultText;
    }


    public void addData(){
        Cursor res = myDb.getSumOfMark();
        if (res.moveToFirst()) {
            do {
                if(res.getString(0).equals("+"))profit = res.getFloat(1);
                if(res.getString(0).equals("-"))outgo = res.getFloat(1);
                balance = profit - outgo;
                textProfit.setText("Profit: "+profit);
                textOutgo.setText("Outgo: "+outgo);
                textBalance.setText("Balance: "+balance);
            }
            while (res.moveToNext());
        }
        outgo=0;
        profit=0;
        balance=0;
        res.close();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(myDb!=null) addData();
    }

}


