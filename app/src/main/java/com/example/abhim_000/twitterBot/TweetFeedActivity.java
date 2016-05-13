package com.example.abhim_000.twitterBot;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class TweetFeedActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private TweetFeedController controller;
    private String params;
    public float[] speechRates = new float[]{(float) 2.0, (float) 1.0, (float) 0.5};
    public float[] pitches = new float[]{(float) 2.0, (float) 1.0, (float) 0.5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_feed);

        params = Singleton.getInstance().getString();
        controller = new TweetFeedController(this);
        controller.getTweetList(params);
        Button SpeechButton = (Button) findViewById(R.id.speechSettingsButton);

        SpeechButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), LanguageSettingsActivity.class);
            startActivity(intent);
            }
            });

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onInit(int status) {
                if (status == TextToSpeech.ERROR) {
                    Toast.makeText(getApplicationContext(), "Text to speech error", Toast.LENGTH_LONG).show();
                }
            }
            });
        String listUpdates = Singleton.getInstance().getSound();
        updateSoundSettings(listUpdates);
        Button JsonButton = (Button) findViewById(R.id.jsonButton);
        JsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.getTweetList(params);
            }
        });

        Button SearchButton = (Button) findViewById(R.id.searchbutton);
        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchParamActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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

    private void updateSoundSettings(String value) {
        int speechRate = Integer.parseInt(value.substring(0,1));
        tts.setSpeechRate(speechRates[speechRate]);
        int pitch = Integer.parseInt(value.substring(1,2));
        tts.setPitch(pitches[pitch]);
    }
}