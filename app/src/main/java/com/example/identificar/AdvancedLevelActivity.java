package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.identificar.models.Cars;
import com.example.identificar.models.CountDown;


import java.util.Random;

public class AdvancedLevelActivity extends AppCompatActivity {

    ImageView carPic1;
    ImageView carPic2;
    ImageView carPic3;
    EditText car1EditText;
    EditText car2EditText;
    EditText car3EditText;
    TextView wrong1;
    TextView wrong2;
    TextView wrong3;
    Button btnSubmit;
    TextView score;
    TextView correct;
    Cars cars;

    int scoreValue = 0;
    int currentIncorrectGuess = 4;

    int randomInt1;
    int randomInt2;
    int randomInt3;

    boolean switchChecked;

    CountDown countTimer;
    TextView timerTextView;

    int milliSec = 20000;
    int countDownInterval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        //getting the values passed from the main activity
        Intent intent = getIntent();
        if(intent != null) {
            switchChecked = intent.getBooleanExtra("isOn", false);
        }

        /*
        Initializations
         */

        // initializing image views
        carPic1 = (ImageView) findViewById(R.id.imgOneAdvanced);
        carPic2 = (ImageView) findViewById(R.id.imgTwoAdvanced);
        carPic3 = (ImageView) findViewById(R.id.imgThreeAdvanced);
        // initializing edit text views
        car1EditText = (EditText) findViewById(R.id.imgEditText1);
        car2EditText = (EditText) findViewById(R.id.imgEditText2);
        car3EditText = (EditText) findViewById(R.id.imgEditText3);
        // initializing wrong message text views
        wrong1 = (TextView) findViewById(R.id.wrong1);
        wrong2 = (TextView) findViewById(R.id.wrong2);
        wrong3 = (TextView) findViewById(R.id.wrong3);
        correct = (TextView) findViewById(R.id.correct);

        btnSubmit = (Button) findViewById(R.id.btnSubmitAdvanced);
        score = (TextView) findViewById(R.id.scoreAdvaced);

        timerTextView = (TextView) findViewById(R.id.textTimerAdvance);

        cars = Cars.getInstance();

        countTimer = new CountDown(milliSec, countDownInterval);

        /*
        Setups
         */

        generateRandomResources();
        wrong1.setText(null);
        wrong2.setText(null);
        wrong3.setText(null);
        correct.setText(null);
        score.setText("0");

        countTimer.initializeCountDown(switchChecked, timerTextView, btnSubmit, this);
        countTimer.startCount();
    }

    public void generateRandomResources(){

        //setting a random number to not to be corresponding with other images generated
        //always generates three different random numbers
        Random random = new Random();
        randomInt1 = random.nextInt(30);
        randomInt2 = random.nextInt(30);
        while (randomInt2 == randomInt1){
            randomInt2 = random.nextInt(30);
        }
        randomInt3 = random.nextInt(30);
        while (randomInt3 == randomInt2 || randomInt3 == randomInt1){
            randomInt3 = random.nextInt(30);
        }

        //setting the image view according to the random number generator
        carPic1.setImageResource(cars.getCarIds()[randomInt1]);
        carPic1.setTag(cars.getMakes()[randomInt1]); //setting a tag to save its make string value
        carPic2.setImageResource(cars.getCarIds()[randomInt2]);
        carPic2.setTag(cars.getMakes()[randomInt2]);
        carPic3.setImageResource(cars.getCarIds()[randomInt3]);
        carPic3.setTag(cars.getMakes()[randomInt3]);


    }

    public void onAdvancedSubmitPressed(View view) {

        if(btnSubmit.getText().toString().equalsIgnoreCase("submit")) {
            if (!car1EditText.getText().toString().isEmpty() ||
                    !car2EditText.getText().toString().isEmpty() ||
                    !car3EditText.getText().toString().isEmpty()
            ) {

                // checking if all the car names given are correct
                if (compareCarNames(car1EditText, cars.getMakes()[randomInt1]) &&
                        compareCarNames(car2EditText, cars.getMakes()[randomInt2]) &&
                        compareCarNames(car3EditText, cars.getMakes()[randomInt3])) {

                    // is enabled are checked to execute the score by one when user re-corrects without exceeding the chances
                    if(car1EditText.isEnabled() || car2EditText.isEnabled() || car3EditText.isEnabled()) {
                        if(car1EditText.isEnabled()){
                            scoreValue ++;
                            score.setText(Integer.toString(scoreValue));
                        }
                        if(car2EditText.isEnabled()){
                            scoreValue ++;
                            score.setText(Integer.toString(scoreValue));
                        }
                        if(car3EditText.isEnabled()){
                            scoreValue ++;
                            score.setText(Integer.toString(scoreValue));
                        }

                    }
                    correct.setText(R.string.correct);
                    correct.setTextColor(getResources().getColor(R.color.identificarGreen));

                    //setting edit text views green custhey are correct
                    car1EditText.setTextColor(getResources().getColor(R.color.identificarGreen));
                    car2EditText.setTextColor(getResources().getColor(R.color.identificarGreen));
                    car3EditText.setTextColor(getResources().getColor(R.color.identificarGreen));

                    //making edittext views uneditable
                    car1EditText.setEnabled(false);
                    car2EditText.setEnabled(false);
                    car3EditText.setEnabled(false);

                    btnSubmit.setText(R.string.btn_next);
                }else{
                    // if some car names are not correct.

                    currentIncorrectGuess --;
                    correct.setText("left: " + Integer.toString(currentIncorrectGuess - 1));
                    correct.setTextColor(getResources().getColor(R.color.identificarRed));

                    if(!compareCarNames(car1EditText, cars.getMakes()[randomInt1])){
                        car1EditText.setTextColor(getResources().getColor(R.color.identificarRed));
                    }else{
                        car1EditText.setTextColor(getResources().getColor(R.color.identificarGreen));

                        //score adds only when user typed the on the first check
                        if(car1EditText.isEnabled()){
                            scoreValue ++;
                            score.setText(Integer.toString(scoreValue));
                        }
                        car1EditText.setEnabled(false);

                    }
                    if(!compareCarNames(car2EditText, cars.getMakes()[randomInt2])){
                        car2EditText.setTextColor(getResources().getColor(R.color.identificarRed));
                    }else{
                        car2EditText.setTextColor(getResources().getColor(R.color.identificarGreen));
                        if(car2EditText.isEnabled()){
                            scoreValue ++;
                            score.setText(Integer.toString(scoreValue));
                        }
                        car2EditText.setEnabled(false);
                    }
                    if(!compareCarNames(car3EditText, cars.getMakes()[randomInt3])){
                        car3EditText.setTextColor(getResources().getColor(R.color.identificarRed));
                    }else{
                        car3EditText.setTextColor(getResources().getColor(R.color.identificarGreen));
                        if(car3EditText.isEnabled()){
                            scoreValue ++;
                            score.setText(Integer.toString(scoreValue));
                        }
                        car3EditText.setEnabled(false);

                    }

                    if(currentIncorrectGuess == 0) {
                        correct.setText(null);
                        if(!compareCarNames(car1EditText, cars.getMakes()[randomInt1]))
                            wrong1.setText(cars.getMakes()[randomInt1]);
                        if(!compareCarNames(car2EditText, cars.getMakes()[randomInt2]))
                            wrong2.setText(cars.getMakes()[randomInt2]);
                        if(!compareCarNames(car3EditText, cars.getMakes()[randomInt3]))
                            wrong3.setText(cars.getMakes()[randomInt3]);

                        correct.setTextColor(getResources().getColor(R.color.identificarRed));
                        correct.setText(R.string.wrong);
                        btnSubmit.setText(R.string.btn_next);
                    }
                }

            } else {
                Toast.makeText(this, "Please fill all the text boxes", Toast.LENGTH_SHORT).show();
            }



        }else if(btnSubmit.getText().toString().equalsIgnoreCase("next")){

            countTimer.stopCount();

            car1EditText.setTextColor(getResources().getColor(R.color.identificarPrimary));
            car2EditText.setTextColor(getResources().getColor(R.color.identificarPrimary));
            car3EditText.setTextColor(getResources().getColor(R.color.identificarPrimary));
            car1EditText.setText(null);
            car2EditText.setText(null);
            car3EditText.setText(null);
            car1EditText.setEnabled(true);
            car2EditText.setEnabled(true);
            car3EditText.setEnabled(true);
            correct.setText(null);
            wrong1.setText(null);
            wrong2.setText(null);
            wrong3.setText(null);
            //scoreValue = 0;
            currentIncorrectGuess = 4;

            generateRandomResources();
            btnSubmit.setText(R.string.btn_submit);

            countTimer.initializeCountDown(switchChecked, timerTextView, btnSubmit, this);
            countTimer.startCount();
        }

        // TODO: ignore the spaces when getting user inputs
        //TODO: implement the count down for all the attempts
    }

    public boolean compareCarNames(EditText e1, String carName){
        return e1.getText().toString().equalsIgnoreCase(carName);
    }

    @Override
    protected void onStop() {
        super.onStop();
        countTimer.stopCount();
    }
}