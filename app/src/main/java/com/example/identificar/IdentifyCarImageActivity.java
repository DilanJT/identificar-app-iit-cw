package com.example.identificar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.identificar.models.Cars;

import org.w3c.dom.Text;

import java.util.Random;

public class IdentifyCarImageActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    TextView generatedCarModel;
    TextView correctWrong;
    Button btnSubmit;
    Cars cars;

    int randomInt1;
    int randomInt2;
    int randomInt3;
    int randomInt4; //specifically get the make of 3 random generated cars randomly

    boolean switchChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_image);

        //getting the values passed from the main activity
        Intent intent = getIntent();
        if(intent != null) {
            switchChecked = intent.getBooleanExtra("isOn", false);
        }

        /*
        Initializations
         */

        imageView1 = (ImageView) findViewById(R.id.imageOption1);
        imageView2 = (ImageView) findViewById(R.id.imageOption2);
        imageView3 = (ImageView) findViewById(R.id.imageOption3);
        generatedCarModel = (TextView) findViewById(R.id.generatedTextForImageAct);
        btnSubmit = (Button) findViewById(R.id.btnSubmitImageAct);
        correctWrong = (TextView) findViewById(R.id.correctGuessTextViewImage);

        cars = Cars.getInstance();

        /*
        Setups
         */
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        //setting up the random resources
        generateRandomResources();
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
        imageView1.setImageResource(cars.getCarIds()[randomInt1]);
        imageView1.setTag(cars.getMakes()[randomInt1]); //setting a tag to save its make string value
        imageView2.setImageResource(cars.getCarIds()[randomInt2]);
        imageView2.setTag(cars.getMakes()[randomInt2]);
        imageView3.setImageResource(cars.getCarIds()[randomInt3]);
        imageView3.setTag(cars.getMakes()[randomInt3]);


        String[] carsMakes = {cars.getMakes()[randomInt1], cars.getMakes()[randomInt2],
            cars.getMakes()[randomInt3]};

        //out of the random numbers generated a random car make is taken to predict.
        //random car make out of the above carMakes string list is generated

        randomInt4 = random.nextInt(3);

        generatedCarModel.setText(carsMakes[randomInt4]);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imageOption1:
            case R.id.imageOption2:
            case R.id.imageOption3:
                if(v.getTag().toString().equalsIgnoreCase(generatedCarModel.getText().toString())){
                    correctWrong.setText(R.string.correct);
                    correctWrong.setTextColor(getResources().getColor(R.color.identificarGreen));
                }else {
                    correctWrong.setText(R.string.wrong);
                    correctWrong.setTextColor(getResources().getColor(R.color.identificarRed));
                }
                break;
            case R.id.btnSubmitImageAct:
                generateRandomResources();
                correctWrong.setText(null);
                break;
        }
    }
}