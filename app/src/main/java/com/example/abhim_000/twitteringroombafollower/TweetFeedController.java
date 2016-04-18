package com.example.abhim_000.twitteringroombafollower;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TweetFeedController implements Observer {
    public static final String TAG = "twitteringRoombaLog";
    TweetGetter tweetGetter = new TweetGetter();
    ApiCredentials apiCredentials = new ApiCredentials();
    List<TextView> textViews = new ArrayList<>();

    public TweetFeedController() {
        tweetGetter.addObserver(this);
    }

    String latestTweet;
    private String getLatestTweet() {
        return latestTweet;
    }
    private void setLatestTweet(String latestTweet) {
        this.latestTweet = latestTweet;
    }
    public void subscribe(TextView view) {
        textViews.add(view);
    }

    public String getTweets(){
        String url = this.apiCredentials.getApiUrl();
        Log.d(TAG, "going to: " + url);
        this.tweetGetter.readJsonFromUrl(url);
        return getLatestTweet();
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.d(TAG,"updating");
        String tweet = (String)data;
        setLatestTweet(tweet);
        for (TextView t : textViews) {
            Log.d(TAG,"really updating");
            t.setText(getLatestTweet());
        }
    }
}