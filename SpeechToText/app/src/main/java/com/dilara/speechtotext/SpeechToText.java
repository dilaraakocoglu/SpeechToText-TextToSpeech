package com.dilara.speechtotext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class SpeechToText extends AppCompatActivity {

    public ImageButton buttonVoice;
    public EditText incomingData;
    public Button buttonSend;
    public Intent intent;
    public static final int requestCodeVoice = 1;
    public SpeechRecognizer recognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        buttonVoice = findViewById(R.id.buttonVoice);
        incomingData = findViewById(R.id.incomingData);
        buttonSend = findViewById(R.id.sendButton);

        incomingData.setEnabled(false);
    }

    public void onClick(View view){

        if (view.getId()== buttonVoice.getId()){

            intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // intent i oluşturduk sesi tanıyabilmesi için
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

            try{
                startActivityForResult(intent, requestCodeVoice);  // activityi başlattık belirlediğimiz sabit değer ile birlikte
            }catch(ActivityNotFoundException e)
            {
                // activity bulunamadığı zaman hatayı göstermek için alert dialog kullandım
                e.printStackTrace();
                AlertDialog.Builder builder = new AlertDialog.Builder(SpeechToText.this);
                builder.setMessage("Üzgünüz Telefonunuz bu sistemi desteklemiyor!")
                        .setTitle("Yves Rocher")
                        .setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }

        else if (view.getId() == buttonSend.getId()){

            Paylaş();

        }
    }
    protected void Paylaş() {


        String txt = incomingData.getText().toString();

        if(txt.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Boş paylaşım yapılamaz!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent share_intent = new Intent(android.content.Intent.ACTION_SEND); // intenti oluşturdum
            share_intent.setType("text/plain");
            share_intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Yves Rocher");        // mesaj konusu
            share_intent.putExtra(android.content.Intent.EXTRA_TEXT, incomingData.getText().toString()); // mesaj içeriği olarak, söylediğim söz gönderilecek
            startActivity(Intent.createChooser(share_intent, "Paylaşmak için birini seçiniz"));  // paylaşmak istediğim platformu seçiyorum
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case requestCodeVoice: {

                if (resultCode == RESULT_OK && data != null)
                {
                    // intent boş olmadığında ve sonuç tamam olduğu anda konuşmayı alıp listenin içine attım
                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    incomingData.setText(speech.get(0));
                }
                break;
            }

        }
    }

}