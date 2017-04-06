package com.example.hansel.guessinggameappdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private EditText guessField;



    private int secretNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random random = new Random();
        int min = 1;
        int max = 10;
        secretNumber = min + random.nextInt(max);
        statusText = (TextView) findViewById(R.id.statusText);
        guessField = (EditText) findViewById(R.id.guessField);

        statusText.setText("hello world!");

        guessField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence string, int start, int before, int count) {

                try{
                    int value = Integer.parseInt(string.toString());


                    if (value == secretNumber) {
                        statusText.setText("you won!! you rulz");
                    }

                    if (value < secretNumber){
                        statusText.setText("try bigger");
                    }

                    if (value > secretNumber){
                        statusText.setText("try smaller");
                    }
                } catch(Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void handler(View view){
        Intent intent = new Intent(this, SecondaryActivity.class);
        startActivity(intent);
    }
}
