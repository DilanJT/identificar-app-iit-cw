package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;

    Button btnIdentifyModel;
    Button btnIdentifyImage;
    Button btnHints;
    Button btnAdvancedLevel;
    Switch aSwitch;
    Boolean switchChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intializing buttons and setting on click listeners

        btnIdentifyModel = (Button) findViewById(R.id.btnIdentifyCarMake);
        btnIdentifyModel.setOnClickListener(this);

        btnIdentifyImage = (Button) findViewById(R.id.btnIdentifyCarImage);
        btnIdentifyImage.setOnClickListener(this);

        btnHints = (Button) findViewById(R.id.btnHints);
        btnHints.setOnClickListener(this);

        btnAdvancedLevel = (Button) findViewById(R.id.btnAdvancedLevel);
        btnAdvancedLevel.setOnClickListener(this);

        aSwitch = (Switch) findViewById(R.id.switchTimer);

        //listener to the switch for the timer.
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch.isChecked()){
                    switchChecked = true;
                }else{
                    switchChecked = false;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        //setting the the intent by passing the switch value.
        //according to the value activities' timer can decide weather to operate or not.
        switch(v.getId()){
            case R.id.btnIdentifyCarMake:
                intent = new Intent(this, IdentifyCarModelActivity.class);
                intent.putExtra("isOn", switchChecked);
                break;
            case R.id.btnIdentifyCarImage:
                intent = new Intent(this, IdentifyCarImageActivity.class);
                intent.putExtra("isOn", switchChecked);
                break;
            case R.id.btnHints:
                intent = new Intent(this, HintsActivity.class);
                intent.putExtra("isOn", switchChecked);
                break;
            case R.id.btnAdvancedLevel:
                intent = new Intent(this, AdvancedLevelActivity.class);
                intent.putExtra("isOn", switchChecked);
                break;
        }

        startActivityForResult(intent, TEXT_REQUEST);
    }
}