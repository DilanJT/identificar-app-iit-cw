package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.identificar.models.Cars;
import com.example.identificar.models.CountDown;

import java.util.Random;

public class HintsActivity extends AppCompatActivity {

    ImageView carPic;
    TextView dashedText;
    EditText guessText;
    Button btnSubmit;
    int randomInt;
    String generatedCarMake;
    Cars cars;
    final int MAX_GUESSES = 3;
    int currentIncorrectGuess = 4;
    boolean found = false;
    boolean present = false;

    TextView correctWrong;
    TextView correctAnswer;
    TextView timerTextView;

    boolean switchChecked;
    CountDown countTimer;

    int milliSec = 5000;
    int countDownInterval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        //getting the values passed from the main activity
        Intent intent = getIntent();
        if(intent != null) {
            switchChecked = intent.getBooleanExtra("isOn", false);
        }

        /*
        Initialization of the views
        */

        carPic = (ImageView) findViewById(R.id.imgHintsView);
        btnSubmit = (Button) findViewById(R.id.btnHintsSubmit);
        correctWrong = (TextView) findViewById(R.id.correctTextViewHints);
        correctAnswer = (TextView) findViewById(R.id.resultTextViewHints);
        dashedText = (TextView) findViewById(R.id.correctGuessTextView);
        guessText = (EditText) findViewById(R.id.editTextGuessHints);
        timerTextView = (TextView) findViewById(R.id.textTimerHints);

        cars = Cars.getInstance();

        countTimer = new CountDown(milliSec, countDownInterval);

        /*
        SETUPS
        */

        Random random = new Random();
        randomInt = random.nextInt(30);

        carPic.setImageResource(cars.getCarIds()[randomInt]);
        generatedCarMake = cars.getMakes()[randomInt];
        dashedText.setText(null); //setting the dashes text view null at the first place
        setDashedText(); //setting the dashes according to the car make text

        countTimer.initializeCountDown(switchChecked, timerTextView, this);
        countTimer.startCount();

        timerTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(timerTextView.getText().toString().equalsIgnoreCase("TimesUp!")){
                    //checking the status of the attempts left

                    if(btnSubmit.getText().toString().equalsIgnoreCase("Submit")){
                        present = false;

                        String trimmedString = generatedCarMake.replaceAll("\\s+", "");
                        char[] generatedCarMakedChars = trimmedString.toCharArray();
                        String gTextChar = guessText.getText().toString();
                        char[] dashesChar = dashedText.getText().toString().toCharArray();

                        submitFunctionality( trimmedString,  gTextChar,  dashesChar, generatedCarMakedChars, present);
                        countTimer.stopCount();
                        countTimer.initializeCountDown(switchChecked, timerTextView, getApplicationContext());
                        countTimer.startCount();
                    }else if(btnSubmit.getText().toString().equalsIgnoreCase("Next")){
                        nextFunctionality();
                    }
                }
            }
        });

    }

    public void setDashedText(){
        int dashNumber = generatedCarMake.length();
        String dashes = "";
        String trimmedString = generatedCarMake.replaceAll("\\s+", "");

        for(int i = 0; i < dashNumber; i++) {
            dashes += "-";
        }

        dashedText.setText(dashes);

    }

    public void onSubmitClicked(View view) {

        present = false;

        String trimmedString = generatedCarMake.replaceAll("\\s+", "");
        char[] generatedCarMakedChars = trimmedString.toCharArray();
        String gTextChar = guessText.getText().toString();
        char[] dashesChar = dashedText.getText().toString().toCharArray();


        if(btnSubmit.getText().toString().equalsIgnoreCase("Submit")) {

//                Log.i("Check :", Character.toString(gTextChar.charAt(0)));
//                for (int i = 0; i < trimmedString.length(); i++) {
//                    if (gTextChar.equalsIgnoreCase(Character.toString(generatedCarMakedChars[i]))) {
//                        //have checked weather we have correctly guessed it earlier
//                        if (!Character.toString(dashesChar[i]).equalsIgnoreCase(gTextChar)) {
//                            dashesChar[i] = gTextChar.charAt(0); // here 0 is because it is the only string guess
//                        }
//                        present = true;
//                    }
//                }
//
//            if(!present){
//                currentIncorrectGuess --;
//                correctAnswer.setTextColor(getResources().getColor(R.color.identificarRed));
//                correctAnswer.setText((currentIncorrectGuess - 1) + " wrong attepts left");
//            }
//            if(currentIncorrectGuess == 0) {
//                guessText.setText(null);
//                correctWrong.setText(R.string.wrong);
//                correctWrong.setTextColor(getResources().getColor(R.color.identificarRed));
//                correctAnswer.setText(generatedCarMake);
//                correctAnswer.setTextColor(getResources().getColor(R.color.identificarYellow));
//                btnSubmit.setText(R.string.btn_next);
//                return;
//            }
//
//
//            String stringDashes = new String(dashesChar);
//            dashedText.setText(stringDashes);
//            guessText.setText(null);
//
//            //checks if all the values in dashed text corresponds with the generated car make text
//            if(trimmedString.equalsIgnoreCase(dashedText.getText().toString())) {
//                found = true;
//                correctWrong.setText(R.string.correct);
//                correctWrong.setTextColor(getResources().getColor(R.color.identificarGreen));
//                correctAnswer.setText(null);
//                btnSubmit.setText(R.string.btn_next);
//            }

            submitFunctionality( trimmedString,  gTextChar,  dashesChar, generatedCarMakedChars, present);

            //System.out.println("No incorrect guess chances left.");
        }else if(btnSubmit.getText().toString().equalsIgnoreCase("Next")){

//            countTimer.stopCount();
//
//            currentIncorrectGuess = 4;
//            correctAnswer.setText(null);
//            correctWrong.setText(null);
//            Random random = new Random();
//            randomInt = random.nextInt(30);
//
//            carPic.setImageResource(cars.getCarIds()[randomInt]);
//            generatedCarMake = cars.getMakes()[randomInt];
//            setDashedText();
//            btnSubmit.setText(R.string.btn_submit);
//
//            countTimer.initializeCountDown(switchChecked, timerTextView, btnSubmit, this);
//            countTimer.startCount();

            nextFunctionality();
        }

        //TODO: fix the submit button clicked in the null state
        //TODO: fix the name with spaces
        //TODO: implement the count down for all the attempts
    }

    public void submitFunctionality(String trimmedString, String gTextChar, char[] dashesChar, char[] generatedCarMakedChars, boolean present){

        if(!guessText.getText().toString().isEmpty()) {
            Log.i("Check :", Character.toString(gTextChar.charAt(0)));
            for (int i = 0; i < trimmedString.length(); i++) {
                if (gTextChar.equalsIgnoreCase(Character.toString(generatedCarMakedChars[i]))) {
                    //have checked weather we have correctly guessed it earlier
                    if (!Character.toString(dashesChar[i]).equalsIgnoreCase(gTextChar)) {
                        dashesChar[i] = gTextChar.charAt(0); // here 0 is because it is the only string guess
                    }
                    present = true;
                }
            }
        }

        if(!present){
            currentIncorrectGuess --;
            correctAnswer.setTextColor(getResources().getColor(R.color.identificarRed));
            correctAnswer.setText((currentIncorrectGuess - 1) + " wrong attepts left");
        }
        if(currentIncorrectGuess == 0) {
            guessText.setText(null);
            correctWrong.setText(R.string.wrong);
            correctWrong.setTextColor(getResources().getColor(R.color.identificarRed));
            correctAnswer.setText(generatedCarMake);
            correctAnswer.setTextColor(getResources().getColor(R.color.identificarYellow));
            btnSubmit.setText(R.string.btn_next);
            return;
        }


        String stringDashes = new String(dashesChar);
        dashedText.setText(stringDashes);
        guessText.setText(null);

        //checks if all the values in dashed text corresponds with the generated car make text
        if(trimmedString.equalsIgnoreCase(dashedText.getText().toString())) {
            found = true;
            correctWrong.setText(R.string.correct);
            correctWrong.setTextColor(getResources().getColor(R.color.identificarGreen));
            correctAnswer.setText(null);
            btnSubmit.setText(R.string.btn_next);
        }
    }

    public void nextFunctionality(){
        countTimer.stopCount();

        currentIncorrectGuess = 4;
        correctAnswer.setText(null);
        correctWrong.setText(null);
        Random random = new Random();
        randomInt = random.nextInt(30);

        carPic.setImageResource(cars.getCarIds()[randomInt]);
        generatedCarMake = cars.getMakes()[randomInt];
        setDashedText();
        btnSubmit.setText(R.string.btn_submit);

        countTimer.initializeCountDown(switchChecked, timerTextView, this);
        countTimer.startCount();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countTimer.stopCount();
        System.out.println("On stop printed");
    }
}