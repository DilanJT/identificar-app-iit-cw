package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class IdentifyCarModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_model);

        Spinner dropdown = (Spinner) findViewById(R.id.spinner);
        String[] list = {"Option 1", "Option 2", "Option 3"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.activity_identify_car_model, list);
        dropdown.setAdapter(arrayAdapter);

    }
}