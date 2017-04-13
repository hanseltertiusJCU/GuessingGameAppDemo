package com.example.hansel.guessinggameappdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class SecondaryActivity extends AppCompatActivity {

    private SeekBar minBar;
    private SeekBar maxBar;
    private TextView minAmount;
    private TextView maxAmount;
    private int minValue;
    private int maxValue;
    private NumberFormat textFormatter =NumberFormat.getNumberInstance();
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        preferences = getSharedPreferences("limit",MODE_PRIVATE);

        minBar = (SeekBar) findViewById(R.id.minBar);
        maxBar = (SeekBar) findViewById(R.id.maxBar);
        minAmount = (TextView) findViewById(R.id.minAmount);
        maxAmount = (TextView) findViewById(R.id.maxAmount);

        minValue= preferences.getInt("minimum",1);
        maxValue= preferences.getInt("maximum",10);
        if(minValue==1){
            minBar.setProgress(0);
        }
        else {
            minBar.setProgress(preferences.getInt("minimum",0)*11+1);
        }
        if(maxValue==10) {
            maxBar.setProgress(0);
        }
        else {
            maxBar.setProgress((preferences.getInt("maximum",0)*10-100)/9);
        }
        minAmount.setText(textFormatter.format(minValue));
        maxAmount.setText(textFormatter.format(maxValue));

        minBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minValue= 1+ progress/12;
                minAmount.setText(textFormatter.format(minValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maxBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxValue= (progress*9 +100)/10;
                maxAmount.setText(textFormatter.format(maxValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void setButton(View view){
        if(minBar.getProgress()==0){
            minValue=1;
        }
        if(maxBar.getProgress()==0){
            maxValue=10;
        }
        preferences.edit()
                .putInt("minimum",minValue)
                .putInt("maximum",maxValue)
                .apply();
        finish();
    }
}