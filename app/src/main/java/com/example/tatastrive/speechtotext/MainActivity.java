package com.example.tatastrive.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final int REQ_CODE_SPEECH_INPUT = 23;
    Button Pbutton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pbutton=findViewById(R.id.push_button);
        textView=findViewById(R.id.Textview1);

        Pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                micclick();
            }
        });
    }

    private void micclick() {
            //RecognizerIntent do some recognization for speech
        //PutExtra is used to pass multiple parameter and multiple Messages in a single intent
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);                             //Perform an action of speech Recognition
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);         //Support multiple languages and maintain lang. free form
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());                  //using default system lang.
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hi speak something");    //passing additional message or additional value
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT); //we pass object of intent and intent code in startactivity for result,startActivityForResult method is followed by ONActivityresult method
        }
        catch (ActivityNotFoundException a)
        {

        }
    }

    //receiving speech input
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case REQ_CODE_SPEECH_INPUT:{
                if(resultCode==RESULT_OK && null!=data)
                {
                    ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText(result.get(0));
                    //save speech to text file
                }
                break;
            }
        }
    }
}
