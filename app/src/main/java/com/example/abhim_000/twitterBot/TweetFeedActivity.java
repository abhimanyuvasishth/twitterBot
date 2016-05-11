package com.example.abhim_000.twitterBot;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.LocalBroadcastManager;
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
    private TweetFeedController controller = new TweetFeedController(this);
    private String params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_feed);
        Bundle bundle = getIntent().getExtras();
        params = bundle.getString("message");
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
                    tts.setLanguage(Locale.ITALIAN);
                    tts.setSpeechRate((float) 2.0);
                    tts.setPitch((float) 0.5);
                }
            }
        });

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
}