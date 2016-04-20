package com.example.abhim_000.twitterBot;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;

public class TweetGetter extends Observable {
    public static final String TAG = "twitteringRoombaLog";

    String tweet;
    public String getTweet() {return tweet; }
    public void setTweet(String tweet) {
        Log.d(TAG, "set tweet!");
        this.tweet = tweet;
        setChanged();
        notifyObservers(this.tweet);
    }

    public String readJsonFromUrl(String url) {
        Log.d(TAG, "Getter called");
        new TweetGetterTask().execute(url);
        return getTweet();
    }

    private class TweetGetterTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String[] params) {
            Log.d(TAG, "Connecting");
            HttpURLConnection urlConnection;
            try {
                urlConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                Log.d(TAG, "Connection opened");
                urlConnection.setRequestMethod("GET");
                Log.d(TAG, "GET");
                int statusCode = urlConnection.getResponseCode();
                Log.d(TAG, "StatusCode: " + statusCode);
                if (statusCode == 200) {
                    urlConnection.connect();
                    Log.d(TAG, "Connected");
                    JsonReader reader = new JsonReader(
                            new InputStreamReader(urlConnection.getInputStream()));
                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(reader);
                    Log.d(TAG, "parsed");
                    Gson gson = new Gson();
                    Tweet p = gson.fromJson(element.getAsJsonArray().get(0), Tweet.class);
                    Log.d(TAG, element.toString());
                    return p.toString();
                }
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
                return "Exception";
            }
            return "ERROR";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG,"calling set tweet");
            setTweet(result);
        }
    }
}