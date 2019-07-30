package com.dilara.speechtotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    TextView tw;
    Button convertButton;
    EditText editTextData;

    TextToSpeech textToSpeech;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        tw = findViewById(R.id.textView);
        convertButton = findViewById(R.id.convertButton);
        editTextData = findViewById(R.id.editTextData);

        textToSpeech = new TextToSpeech(context,this);

    }

    public void onClick (View view){

        if (view.getId() == convertButton.getId()){

            String text = editTextData.getText().toString();

            if (text == null || text.equals("")){

                Toast.makeText(context,"Boş bırakılamaz! Lütfen veri girişi yapınız.",Toast.LENGTH_LONG).show();
                return; //burada kalır aşağı kodlara gitmez...
            }

            ConvertTextToSpeech(text);
        }
    }

    @Override
    public void onInit(int i) {

        if (i == textToSpeech.SUCCESS){ //veri alımı başarılıysa

            int result = textToSpeech.setLanguage(Locale.getDefault()); // konuşma dili telefonun dili ne ise

            if(result == textToSpeech.LANG_MISSING_DATA || result == textToSpeech.LANG_NOT_SUPPORTED){ //eğer dil verisi alınamadıysa veya dil desteklenmiyorsa

                Toast.makeText(context,"Bu dil desteklenmiyor!",Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(context,"Başarısız!",Toast.LENGTH_LONG).show();
        }
    }

    private void ConvertTextToSpeech(String data) {

        textToSpeech.speak(data,textToSpeech.QUEUE_FLUSH,null);

    }
}
