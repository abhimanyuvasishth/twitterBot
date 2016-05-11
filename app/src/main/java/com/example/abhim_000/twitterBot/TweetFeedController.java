package com.example.abhim_000.twitterBot;

import android.util.Log;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TweetFeedController implements Observer {
    public static final String TAG = "twitteringRoombaLog";
    TweetGetter tweetGetter = new TweetGetter();
    ApiCredentials apiCredentials = new ApiCredentials();
    TweetFeedActivity view;

    public TweetFeedController(TweetFeedActivity tweetFeedActivity) {
        tweetGetter.addObserver(this);
        view = tweetFeedActivity;
    }

    ArrayList<Tweet> tweetsArrayList = new ArrayList<>();
    private void setTweetsArrayList(ArrayList<Tweet> tweetsArrayList) {
        this.tweetsArrayList = tweetsArrayList;
    }

    public void getTweetList(String params){
        this.apiCredentials.setParams(params);
        String url = this.apiCredentials.getApiUrl();
        Log.d(TAG, "going to: " + url);
        this.tweetGetter.getTweetArray(url);
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.d(TAG, "updating");
        ArrayList<Tweet> tweetList = (ArrayList<Tweet>)data;
        setTweetsArrayList(tweetList);
        view.updateTextViews(tweetList);
    }
}