package com.example.noname.mybudget;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.noname.mybudget.Settings.settingsPage;

public class startMenu extends AppCompatActivity implements View.OnClickListener {

    Button btnStart,btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStart:
                Intent start = new Intent(this,mainTabSelectionWindow.class);
                startActivity(start);
                break;
            case R.id.btnSettings:
                Intent start1 = new Intent(this,settingsPage.class);
                startActivity(start1);
                break;
        }

    }

}
