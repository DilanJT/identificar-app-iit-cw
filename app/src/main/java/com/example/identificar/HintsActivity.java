package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    final int MAX_GUESSES = 3;
    int currentIncorrectGuess = 3;
    boolean found = false;
    boolean present = false;

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
            dashes += "-";
        }

        dashedText.setText(dashes);

    }

    public void onSubmitClicked(View view) {

        String trimmedString = generatedCarMake.replaceAll("\\s+", "");
        char[] generatedCarMakedChars = trimmedString.toCharArray();
        String gTextChar = guessText.getText().toString();
        char[] dashesChar = dashedText.getText().toString().toCharArray();


        if(btnSubmit.getText().toString().equalsIgnoreCase("Submit")) {
                if(currentIncorrectGuess == 0) {
                    correctAnswer.setText(generatedCarMake);
                    correctAnswer.setTextColor(getResources().getColor(R.color.identificarYellow));
                    //btnSubmit.setText(R.string.btn_next);
                    return;
                }
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


//                if(present) {
//                    return;
//                }else{
//                    //if present == false
//                    currentIncorrectGuess --;
//                    correctWrong.setText(R.string.wrong);
//                    String guessLeftMessage = Integer.toString(currentIncorrectGuess) + " left";
//                    correctAnswer.setText(guessLeftMessage);
//                    correctWrong.setTextColor(getResources().getColor(R.color.identificarRed));
//                    correctAnswer.setTextColor(getResources().getColor(R.color.identificarRed));
//                }

            String stringDashes = new String(dashesChar);
            dashedText.setText(stringDashes);
            guessText.setText(null);

            //checks if all the values in dashed text corresponds with the generated car make text
            if(trimmedString.equalsIgnoreCase(dashedText.getText().toString())) {
                found = true;
                correctWrong.setText(R.string.correct);
                correctWrong.setTextColor(getResources().getColor(R.color.identificarGreen));
                btnSubmit.setText(R.string.btn_next);
            }

            //System.out.println("No incorrect guess chances left.");
        }else if(btnSubmit.getText().toString().equalsIgnoreCase("Next")){
            Random random = new Random();
            randomInt = random.nextInt(30);

            carPic.setImageResource(cars.getCarIds()[randomInt]);
            generatedCarMake = cars.getMakes()[randomInt];
            setDashedText();
            btnSubmit.setText(R.string.btn_submit);
        }

        //TODO: implement the correct wrong functionality
        //TODO: implement the number of guesses left when he/she guess any wrong char
        //TODO: implement the button text change functionality
      }
}