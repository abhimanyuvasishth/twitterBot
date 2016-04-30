package com.example.abhim_000.twitterBot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class TweetFeedActivity extends AppCompatActivity {
    public static final String TAG = "twitteringRoombaLog";
    private TweetFeedController controller = new TweetFeedController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_feed);

        Button SpeechButton = (Button) findViewById(R.id.speechSettingsButton);
        SpeechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clickedButton");
                Intent intent = new Intent(getApplicationContext(), SpeechSettingsActivity.class);
                startActivity(intent);
            }
        });

        Button JsonButton = (Button) findViewById(R.id.jsonButton);
        JsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked");
                controller.getTweetList();
            }
        });
    }

    public void updateTextViews(ArrayList<Tweet> tweetList){
        ListView listView = (ListView) findViewById(R.id.tweetList);
        TweetAdapter myAdapter = new TweetAdapter(this, R.layout.tweet_item_layout, tweetList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Position :" + position, Toast.LENGTH_LONG).show();
            }
        });
    }
}