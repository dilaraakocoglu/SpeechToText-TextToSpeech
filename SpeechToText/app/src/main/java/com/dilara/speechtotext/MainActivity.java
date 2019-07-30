package com.dilara.speechtotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button speechToText,textToSpeech;
    TextView tw;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speechToText = findViewById(R.id.buttonSpeechToText);
        textToSpeech = findViewById(R.id.buttonTextToSpeech);
        tw = findViewById(R.id.textView);
    }

    public void onClick (View view){

        if (view.getId() == speechToText.getId()){

            intent = new Intent(MainActivity.this,SpeechToText.class);
            startActivity(intent);
        }
        else if(view.getId() == textToSpeech.getId()){

            intent = new Intent(MainActivity.this, TextToSpeechActivity.class);
            startActivity(intent);
        }
    }
}
