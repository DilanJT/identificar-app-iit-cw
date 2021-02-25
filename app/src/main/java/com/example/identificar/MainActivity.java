package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;

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
        Intent intent = null;
        switch(v.getId()){
            case R.id.btnIdentifyCarMake:
                intent = new Intent(this, IdentifyCarModelActivity.class);
                break;
            case R.id.btnIdentifyCarImage:
                intent = new Intent(this, IdentifyCarImageActivity.class);
                break;
            case R.id.btnHints:
                intent = new Intent(this, HintsActivity.class);
                break;
            case R.id.btnAdvancedLevel:
                intent = new Intent(this, AdvancedLevelActivity.class);
                break;
        }

        startActivityForResult(intent, TEXT_REQUEST);
    }
}