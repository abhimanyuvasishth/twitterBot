package com.example.abhim_000.twitteringroombafollower;

import android.util.Log;

public class TweetFeedController {

    public static final String TAG = "twitteringRoombaLog";
    TweetGetter tweetGetter = new TweetGetter();
    String latestTweet;
    ApiCredentials apiCredentials = new ApiCredentials();

    private String getLatestTweet() {
        return latestTweet;
    }

    private void setLatestTweet(String latestTweet) {
        this.latestTweet = latestTweet;
    }

    public String getTweets(){
        String url = this.apiCredentials.getApiUrl();
        Log.d(TAG, "going to: " + url);
        setLatestTweet(this.tweetGetter.readJsonFromUrl(url));
        return getLatestTweet();
    }
}