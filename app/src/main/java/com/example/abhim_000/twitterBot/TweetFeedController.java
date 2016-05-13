package com.example.abhim_000.twitterBot;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TweetFeedController implements Observer {

    TweetGetter tweetGetter = new TweetGetter();
    ApiCredentials apiCredentials = new ApiCredentials();
    TweetFeedActivity view;

    public TweetFeedController(TweetFeedActivity tweetFeedActivity) {
        tweetGetter.addObserver(this);
        view = tweetFeedActivity;
    }

    public void getTweetList(String params){
        this.apiCredentials.setParams(params);
        String url = this.apiCredentials.getApiUrl();
        ArrayList<Tweet> tweetList = Singleton.getInstance().getTweetsArrayList();
        if (tweetList.size() < 1){
            this.tweetGetter.getTweetArray(url);
        }
        else {
            view.updateTextViews(Singleton.getInstance().getTweetsArrayList());
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        ArrayList<Tweet> tweetList = (ArrayList<Tweet>)data;
        Singleton.getInstance().setTweetsArrayList(tweetList);
        view.updateTextViews(Singleton.getInstance().getTweetsArrayList());
    }
}