package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.identificar.models.Cars;

import java.util.Random;

public class HintsActivity extends AppCompatActivity {

    ImageView carPic;
    TextView dashedText;
    EditText guessText;
    Button btnSubmit;
    int randomInt;
    String generatedCarMake;
    Cars cars;

    TextView correctWrong;
    TextView correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        /*
        Initialization of the views
        */

        carPic = (ImageView) findViewById(R.id.imgHintsView);
        btnSubmit = (Button) findViewById(R.id.btnHintsSubmit);
        correctWrong = (TextView) findViewById(R.id.correctTextViewHints);
        correctAnswer = (TextView) findViewById(R.id.resultTextViewHints);
        dashedText = (TextView) findViewById(R.id.correctGuessTextView);
        guessText = (EditText) findViewById(R.id.editTextGuessHints);

        cars = Cars.getInstance();

        /*
        SETUPS
        */

        Random random = new Random();
        randomInt = random.nextInt(30);

        carPic.setImageResource(cars.getCarIds()[randomInt]);
        generatedCarMake = cars.getMakes()[randomInt];
        dashedText.setText(null); //setting the dashes text view null at the first place
        setDashedText(); //setting the dashes according to the car make text
    }

    public void setDashedText(){
        int dashNumber = generatedCarMake.length();
        String dashes = "";
        String trimmedString = generatedCarMake.replaceAll("\\s+", "");

        for(int i = 0; i < dashNumber; i++) {
            dashes += "- ";
        }

        dashedText.setText(dashes);

    }

    public void onSubmitClicked(View view) {
        String trimmedString = generatedCarMake.replaceAll("\\s+", "");
        char[] chars = trimmedString.toCharArray();
        String gTextChar = guessText.getText().toString();


        for(int i = 0; i < trimmedString.length(); i++) {
            if(gTextChar.equalsIgnoreCase(Character.toString(chars[0]))){

            }
        }
    }
}