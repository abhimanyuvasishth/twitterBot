package com.example.abhim_000.twitteringroombafollower;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TweetFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_feed);

        final TextView Enter = (TextView) findViewById(R.id.textView);
        Button ClickButton = (Button) findViewById(R.id.radioButton);
        ClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TweetGetter tweetGetter = new TweetGetter();
                Enter.setText(tweetGetter.latestTweet.toString());
            }
        });
    }
}