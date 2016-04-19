package com.example.abhim_000.twitteringroombafollower;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

// Help for text-speech: http://www.tutorialspoint.com/android/android_text_to_speech.htm

public class TweetFeedActivity extends AppCompatActivity {
    private TweetFeedController controller = new TweetFeedController();
    public static final String TAG = "twitteringRoombaLog";
    private TextToSpeech tts;
    private EditText txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_feed);

        txtText = (EditText) findViewById(R.id.editText);
        final TextView Enter = (TextView) findViewById(R.id.textView);
        controller.subscribe(Enter);

        Button ClickButton = (Button) findViewById(R.id.radioButton);
        ClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked");
                controller.getTweets();
            }
        });

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.ERROR) {
                    Toast.makeText(getApplicationContext(),"Text to speech error", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.d(TAG, "no error");
                    tts.setLanguage(Locale.ITALIAN);
                    tts.setPitch((float) 0.5);
                    tts.setSpeechRate((float) 2.0);
                    Log.d(TAG,tts.getVoices().toString());
                }
            }
        });

        Button btnSpeak = (Button) findViewById(R.id.button);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String toSpeak = txtText.getText().toString();
                Log.d(TAG,"Clicked button");
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        Spinner dropdown = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"Italian", "French", "English"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position){
                    case 0:
                        tts.setLanguage(Locale.ITALIAN);
                        Log.d(TAG, "0");
                        break;
                    case 1:
                        tts.setLanguage(Locale.FRENCH);
                        Log.d(TAG, "1");
                        break;
                    case 2:
                        tts.setLanguage(Locale.UK);
                        Log.d(TAG, "2");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "nothing");
                tts.setLanguage(Locale.ITALIAN);
            }
        });
    }
}