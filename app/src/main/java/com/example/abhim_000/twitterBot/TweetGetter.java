package com.example.abhim_000.twitterBot;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;

public class TweetGetter extends Observable {

    ArrayList<Tweet> tweetList;
    public void setTweetList(ArrayList<Tweet> tweetList){
        this.tweetList = tweetList;
        setChanged();
        notifyObservers(this.tweetList);
    }

    public void getTweetArray(String url) {
        new TweetGetterTask().execute(url);
    }

    private class TweetGetterTask extends AsyncTask<String, Void, String> {
        private ArrayList<Tweet> asyncTweetList = new ArrayList<>();

        @Override
        protected String doInBackground(String[] params) {
            HttpURLConnection urlConnection;
            try {
                urlConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();
                if (statusCode == 200) {
                    urlConnection.connect();
                    JsonReader reader = new JsonReader(
                            new InputStreamReader(urlConnection.getInputStream()));
                    JsonParser parser = new JsonParser();
                    JsonArray elementArray = (JsonArray) parser.parse(reader);
                    for (JsonElement e: elementArray){
                        Gson gson = new Gson();
                        Tweet p = gson.fromJson(e, Tweet.class);
                        this.asyncTweetList.add(this.asyncTweetList.size(), p);
                    }
                    return "Success";
                }
            } catch (Exception e) {
                return "Error";
            }
            return "Error";
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("Success")){
                setTweetList(asyncTweetList);
            }
            else {
                final Tweet err = new Tweet("Couldn't access tweets. " +
                        "Check your internet connection or try another hash tag or screen name.", "", "");
                ArrayList<Tweet> errList = new ArrayList<>();
                errList.add(0,err);
                setTweetList(errList);
            }
        }
    }
}