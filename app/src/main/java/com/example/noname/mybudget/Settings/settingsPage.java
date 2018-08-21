package com.example.noname.mybudget.Settings;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.noname.mybudget.R;

public class settingsPage extends AppCompatActivity implements View.OnClickListener {
    Button btnGreen,btnBlue;
    View back,root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);

        btnGreen = findViewById(R.id.testGreen);
        btnGreen.setOnClickListener(this);
        btnBlue = findViewById(R.id.testBlue);
        btnBlue.setOnClickListener(this);

        back = findViewById(R.id.testLayout);
        root = back.getRootView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.testGreen:
                root.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.testBlue:
                root.setBackgroundColor(getResources().getColor(R.color.newGreen));
                break;
        }
    }
}
