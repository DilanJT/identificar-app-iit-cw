package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.identificar.models.Cars;

import java.util.Random;


public class IdentifyCarModelActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String LOG_TAG = IdentifyCarModelActivity.class.getSimpleName();
    ImageView carPic;
    Button btnSubmit;
    Spinner dropdown;
    String itemSelected;
    int randomInt;
//    String[] carStringList;
//    int[] carIds;
    Cars cars = Cars.getInstance();

    TextView correctWrong;
    TextView correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_model);

        /*
        Primary initializations
        */

        dropdown = (Spinner) findViewById(R.id.spinner);
//        carStringList = new String[]{"Acura", "Alpha Romeo", "Audi", "Bentley", "BMW", "Buick",
//        "Cadilac", "Chevrolet", "Dodge", "Fiat", "Genesis", "GMC", "Hundai", "Jaguar", "Jeep",
//        "Land Rover", "Lexus", "Mercedes", "Mercury", "Mini", "Mitsubishi", "Nissan", "Pontiac",
//        "Porche", "Rolls Royce", "Subaru", "Suzuki", "Tesla", "Toyota", "Volvo"};
//
//        carIds = new int[]{R.drawable.acura, R.drawable.alpha_romeo, R.drawable.audi, R.drawable.bentley,
//        R.drawable.bmw, R.drawable.buick, R.drawable.cadillac, R.drawable.chevrolet, R.drawable.dodge,
//        R.drawable.fiat, R.drawable.genesis, R.drawable.gmc, R.drawable.hundai, R.drawable.jaguar,
//        R.drawable.jeep, R.drawable.land_rover, R.drawable.lexus, R.drawable.mercedes, R.drawable.mercury,
//        R.drawable.mini, R.drawable.mitsubishi, R.drawable.nissan, R.drawable.pontiac, R.drawable.porche,
//        R.drawable.rolls_royce, R.drawable.subaru, R.drawable.suzuki, R.drawable.tesla, R.drawable.toyota,
//        R.drawable.volvo};

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.cars_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(arrayAdapter);

        /*
        Initialization of the views
        */

        carPic = (ImageView) findViewById(R.id.imgModelActivity);
        btnSubmit = (Button) findViewById(R.id.btnModelActivity);
        correctWrong = (TextView) findViewById(R.id.resultTextViewModel);
        correctAnswer = (TextView) findViewById(R.id.correctAnswerModel);

        /*
        SETUPS
        */
        dropdown.setOnItemSelectedListener(this);
        carPic.setImageResource(R.drawable.acura);
        //btnSubmit.setText(getString(R.string.btn_next));

        Random random = new Random();
        randomInt = random.nextInt(30);

        // setting a random image
//        carPic.setImageResource(carIds[randomInt]);
        carPic.setImageResource(cars.getCarIds()[randomInt]);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(LOG_TAG, "Car selected " + dropdown.getSelectedItem().toString());
        itemSelected = dropdown.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void onIdentifyClicked(View view) {
        String btnText = btnSubmit.getText().toString();

        if(btnText.equalsIgnoreCase("identify")){
            // logic when the user clicked on the identified button and its text os changed to next
            if (itemSelected.equalsIgnoreCase(cars.getMakes()[randomInt])){
                correctWrong.setText(R.string.correct);
                correctWrong.setTextColor(getResources().getColor(R.color.identificarGreen));

            }else{
                correctWrong.setText(R.string.wrong);
                correctWrong.setTextColor(getResources().getColor(R.color.identificarRed));
                correctAnswer.setText(cars.getMakes()[randomInt]);
                correctAnswer.setTextColor(getResources().getColor(R.color.identificarYellow));
            }
            btnSubmit.setText(R.string.btn_next);
        }else if(btnText.equalsIgnoreCase("next")){
            //resetting the game state
            correctWrong.setText(null);
            correctAnswer.setText(null);
            Random random = new Random();
            randomInt = random.nextInt(30);
            carPic.setImageResource(cars.getCarIds()[randomInt]);
            btnSubmit.setText(R.string.btn_identify);
        }

    }
}