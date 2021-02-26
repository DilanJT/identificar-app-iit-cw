package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class HintsActivity extends AppCompatActivity {

    ImageView carPic;
    TextView dashedText;
    EditText guessText;
    Button btnSubmit;
    int randomInt;
    String[] carStringList;
    int[] carIds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);
    }
}