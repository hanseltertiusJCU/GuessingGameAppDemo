package com.example.hansel.guessinggameappdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    SeekBar minBar;
    SeekBar maxBar;
    TextView minValue;
    TextView maxValue;
    SharedPreferences minPreferences;
    SharedPreferences maxPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        minBar = (SeekBar) findViewById(R.id.minBar);
        maxBar = (SeekBar) findViewById(R.id.maxBar);
        minValue = (TextView) findViewById(R.id.minValue);
        maxValue = (TextView) findViewById(R.id.maxValue);
        minPreferences = getSharedPreferences("min value", MODE_PRIVATE);
        maxPreferences = getSharedPreferences("max value", MODE_PRIVATE);


        minBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minValue.setText(String.valueOf(progress));
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
                maxValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void handler(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        int minProgress = minPreferences.getInt("min seek bar", 0);
        minBar.setProgress(minProgress);

        int maxProgress = maxPreferences.getInt("max seek bar", 0);
        maxBar.setProgress(maxProgress);
    }

    @Override
    protected void onStop() {
        super.onStop();

        minPreferences.edit()
                .putInt("min seek bar", minBar.getProgress())
                .apply();

        maxPreferences.edit()
                .putInt("max seek bar", maxBar.getProgress())
                .apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
