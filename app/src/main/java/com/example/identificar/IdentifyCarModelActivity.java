package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.identificar.models.CountDown;

import java.util.Random;


public class IdentifyCarModelActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String LOG_TAG = IdentifyCarModelActivity.class.getSimpleName();
    ImageView carPic;
    Button btnSubmit;
    Spinner dropdown;
    String itemSelected;
    int randomInt;
    Cars cars;
    int milliSec = 20000;
    int countDownInterval = 1000;

    TextView correctWrong;
    TextView correctAnswer;
    TextView timerTextView;

    boolean switchChecked;
    boolean isCancelled;
    boolean btnToNextByCount; //changing text of the button in count down timer

//    CountDownTimer countDownTimer;
    CountDown countTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_model);

        //getting the values passed from the main activity
        Intent intent = getIntent();
        if(intent != null) {
            switchChecked = intent.getBooleanExtra("isOn", false);
            // Toast.makeText(this, switchChecked.toString(), Toast.LENGTH_SHORT).show();
        }

        /*
        Primary initializations
        */

        dropdown = (Spinner) findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.cars_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(arrayAdapter);

        /*
        Initialization of the views
        */

        carPic = (ImageView) findViewById(R.id.imgModelActivity);
        btnSubmit = (Button) findViewById(R.id.btnModelActivity);
        correctWrong = (TextView) findViewById(R.id.correctGuessTextViewModel);
        correctAnswer = (TextView) findViewById(R.id.correctAnswerTextViewModel);
        timerTextView = (TextView) findViewById(R.id.textTimerModel);
        cars = Cars.getInstance();

        countTimer = new CountDown(milliSec, countDownInterval);

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


        countTimer.initializeCountDown(switchChecked, timerTextView, btnSubmit, this);
        countTimer.startCount();


        btnSubmit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(IdentifyCarModelActivity.this, "after text changed", Toast.LENGTH_SHORT).show();
                if(timerTextView.getText().toString().equalsIgnoreCase("TimesUp!")){
                    if(btnSubmit.getText().toString().equalsIgnoreCase("next"))
                        onIdentify();
                }

            }
        });

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
            onIdentify();
            btnSubmit.setText(R.string.btn_next);
        }else if(btnText.equalsIgnoreCase("next")){
            //resetting the game state

            countTimer.stopCount();

            correctWrong.setText(null);
            correctAnswer.setText(null);
            Random random = new Random();
            randomInt = random.nextInt(30);
            carPic.setImageResource(cars.getCarIds()[randomInt]);

            countTimer.initializeCountDown(switchChecked, timerTextView, btnSubmit, this);
            countTimer.startCount();


            btnSubmit.setText(R.string.btn_identify);


        }

    }

    public void onIdentify(){
        if (itemSelected.equalsIgnoreCase(cars.getMakes()[randomInt])){
            correctWrong.setText(R.string.correct);
            correctWrong.setTextColor(getResources().getColor(R.color.identificarGreen));

        }else{
            correctWrong.setText(R.string.wrong);
            correctWrong.setTextColor(getResources().getColor(R.color.identificarRed));
            correctAnswer.setText(cars.getMakes()[randomInt]);
            correctAnswer.setTextColor(getResources().getColor(R.color.identificarYellow));
        }
    }

    // On stop functionality to force stop if any ongoing counter is working
    @Override
    protected void onStop() {
        super.onStop();
        countTimer.stopCount();
    }
}