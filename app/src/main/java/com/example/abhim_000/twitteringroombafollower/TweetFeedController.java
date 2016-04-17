package com.example.abhim_000.twitteringroombafollower;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.io.IOException;

public class TweetFeedController {

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
        try {
            setLatestTweet(this.tweetGetter.readJsonFromUrl(url));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return getLatestTweet();
    }
}