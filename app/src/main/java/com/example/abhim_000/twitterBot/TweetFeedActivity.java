package com.example.abhim_000.twitterBot;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

public class TweetFeedActivity extends AppCompatActivity {
    public static final String TAG = "twitteringRoombaLog";
    private TextToSpeech tts;
    private TweetFeedController controller;
    private String params;
    private Locale[] languages;
    private float[] speechRates;
    private float[] pitches;
    private int languageIndex = 0;
    private int speechRateIndex = 0;
    private int pitchIndex = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_feed);
        languages = new Locale[]{Locale.ITALIAN, Locale.FRENCH, Locale.ENGLISH};
        speechRates  = new float[]{(float) 2.0, (float) 1.0, (float) 0.5};
        pitches = new float[]{(float) 2.0, (float) 1.0, (float) 0.5};
        params = Singleton.getInstance().getString();
        controller = new TweetFeedController(this);
        Button SpeechButton = (Button) findViewById(R.id.speechSettingsButton);
        SpeechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clickedButton");
                Intent intent = new Intent(getApplicationContext(), LanguageSettingsActivity.class);
                startActivity(intent);
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
                }
            }
        });
        String listUpdates = Singleton.getInstance().getSound();
        updateSoundSettings(listUpdates);

        Button JsonButton = (Button) findViewById(R.id.jsonButton);
        JsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked");
                controller.getTweetList(params);
            }
        });
    }

    public void updateTextViews(final ArrayList<Tweet> tweetList){
        ListView listView = (ListView) findViewById(R.id.tweetList);
        TweetAdapter myAdapter = new TweetAdapter(this, R.layout.tweet_item_layout, tweetList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = tweetList.get(position).getTweetText();
                String newText = text.replace("#", " hashtag ").replace("@", " at ");
                tts.speak(newText, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    private void updateSoundSettings (String value) {
        // Collect data from the intent and use it
        Log.d(TAG, "received");
        Log.d(TAG, value);
        String string = "hello";
        int language = Integer.parseInt(value.substring(0,1));
        Log.d(TAG, "L: " + language);
        tts.setLanguage(languages[language]);

        int speechRate = Integer.parseInt(value.substring(1,2));
        Log.d(TAG, "R: " + speechRate);
        tts.setSpeechRate(speechRates[speechRate]);

        int pitch = Integer.parseInt(value.substring(2,3));
        Log.d(TAG, "P: " + pitch);
        tts.setPitch(pitches[pitch]);
    }
}