package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnIdentifyModel;
    Button btnIdentifyImage;
    Button btnHints;
    Button btnAdvancedLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIdentifyModel = (Button) findViewById(R.id.btnIdentifyCarMake);
        btnIdentifyModel.setOnClickListener(this);

        btnIdentifyImage = (Button) findViewById(R.id.btnIdentifyCarImage);
        btnIdentifyImage.setOnClickListener(this);

        btnHints = (Button) findViewById(R.id.btnHints);
        btnHints.setOnClickListener(this);

        btnAdvancedLevel = (Button) findViewById(R.id.btnAdvancedLevel);
        btnAdvancedLevel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnIdentifyCarMake:
                break;
            case R.id.btnIdentifyCarImage:
                break;
            case R.id.btnHints:
                break;
            case R.id.btnAdvancedLevel:
                break;
        }
    }
}