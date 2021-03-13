package com.example.identificar.models;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.identificar.IdentifyCarModelActivity;
import com.example.identificar.R;

public class CountDown {

    //this class will helpful in implementing the count down timer functionality to all the game levels (activities)

    private int milliSec;
    private int countDownInterval;
    private CountDownTimer countDownTimer;
    private boolean isFinished;
    private boolean btnTextChangedToNext = false;
    private int counter = 5;
    private CountDown(){}

    public CountDown(int milliSec, int countDownInterval){
        this.milliSec = milliSec;
        this.countDownInterval = countDownInterval;
    }

    public void initializeCountDown(boolean switchChecked, TextView timerTextView, Button btnSubmit, Context context){
        if(switchChecked){
            countDownTimer = new CountDownTimer(milliSec, countDownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText("timer: " + millisUntilFinished / 1000);
                    if((millisUntilFinished / 1000) == 0){
                        onFinish();
                    }

                }

                @Override
                public void onFinish() {
//                    timerTextView.setText("0");
                    Toast.makeText(context, "Times Up!", Toast.LENGTH_SHORT).show();
                    btnSubmit.setText(R.string.btn_next);
                    btnTextChangedToNext = true;
                }
            };
        }
    }

    public void initializeCountDown(boolean switchChecked, TextView timerTextView, Context context){

        //this is created for the use of the hints screen which has multiple attempts
        if(switchChecked){
            countDownTimer = new CountDownTimer(milliSec, countDownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText("timer: " + millisUntilFinished / 1000);
                    if((millisUntilFinished / 1000) == 0){
                        onFinish();
                    }

                }

                @Override
                public void onFinish() {
//                    timerTextView.setText("0");
                    Toast.makeText(context, "Times Up!", Toast.LENGTH_SHORT).show();
                }
            };
        }
    }

    public void startCount(){
        if(countDownTimer != null) {
            isFinished = false;
            countDownTimer.start();
        }
    }

    public void stopCount(){
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = null;
    }

    public CountDownTimer getCountDownTimer(){
        return  countDownTimer;
    }

    public boolean isFinished(){
        return isFinished;
    }

    public boolean isBtnChangedToNext(){
        return btnTextChangedToNext;
    }

    public void setCounter(int counter){
        this.counter = counter;
    }

    public int getCounter(){
        return counter;
    }

}
