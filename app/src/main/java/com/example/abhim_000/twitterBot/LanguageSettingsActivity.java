package com.example.abhim_000.twitterBot;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

// Help for text-speech: http://www.tutorialspoint.com/android/android_text_to_speech.htm

public class LanguageSettingsActivity extends AppCompatActivity {
    public static final String TAG = "twitteringRoombaLog";
    private TextToSpeech tts;
    int[] listUpdates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_settings);

        listUpdates = new int[]{0,2};
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.ERROR) {
                    Toast.makeText(getApplicationContext(),"Text to speech error", Toast.LENGTH_LONG).show();
                }
                else {
                    tts.setLanguage(Locale.ENGLISH);
                    tts.setPitch((float) 0.5);
                    tts.setSpeechRate((float) 2.0);
                }
            }
        });

        Button btnSpeak = (Button) findViewById(R.id.button);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String toSpeak = "I am a twitter bot";
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        Spinner dropdownSpeed = (Spinner) findViewById(R.id.spinnerSpeed);
        String[] speedItems = new String[]{"Fast", "Medium", "Slow"};
        ArrayAdapter<String> speedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, speedItems);
        dropdownSpeed.setAdapter(speedAdapter);
        dropdownSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position){
                    case 0:
                        tts.setSpeechRate((float) 2.0);
                        break;
                    case 1:
                        tts.setSpeechRate((float) 1.0);
                        break;
                    case 2:
                        tts.setSpeechRate((float) 0.5);
                        break;
                }
                updateTweetLanguage(position,0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tts.setPitch((float) 2.0);
            }
        });

        Spinner dropdownPitch = (Spinner) findViewById(R.id.spinnerPitch);
        String[] pitchItems = new String[]{"High", "Medium", "Low"};
        ArrayAdapter<String> pitchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pitchItems);
        dropdownPitch.setAdapter(pitchAdapter);
        dropdownPitch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        tts.setPitch((float) 2.0);
                        break;
                    case 1:
                        tts.setPitch((float) 1.0);
                        break;
                    case 2:
                        tts.setPitch((float) 0.5);
                        break;
                }
                updateTweetLanguage(position, 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tts.setPitch((float) 0.5);
            }
        });

        Button btnConfirm = (Button) findViewById(R.id.buttonConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendString = "";
                for (int i : listUpdates) {sendString += i;}
                Intent intent = new Intent(getApplicationContext(), TweetFeedActivity.class);
                Singleton.getInstance().setSound(sendString);
                startActivity(intent);
            }
        });
    }

    private void updateTweetLanguage(int position, int index){
        listUpdates[index] = position;
    }
}