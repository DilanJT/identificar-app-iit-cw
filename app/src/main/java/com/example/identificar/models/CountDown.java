package com.example.identificar.models;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.identificar.IdentifyCarModelActivity;
import com.example.identificar.R;

public class CountDown {

    private int milliSec;
    private int countDownInterval;
    private CountDownTimer countDownTimer;
    private boolean isFinished;

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
                    isFinished = false;
                    timerTextView.setText("timer: " + millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("done");
                    Toast.makeText(context, "Times Up!", Toast.LENGTH_SHORT).show();
                    btnSubmit.setText(R.string.btn_next);
                    isFinished = true;
                }
            };
        }
    }

    public void startCount(){
        if(countDownTimer != null) {
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
}
