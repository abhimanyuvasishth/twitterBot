package com.example.abhim_000.twitteringroombafollower;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TweetGetter {
    String tweet;

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public static final String TAG = "twitteringRoombaLog";

    public String readJsonFromUrl(String url) {
        Log.d(TAG, "Getter called");
        new TweetGetterTask().execute(url);
        return getTweet();
    }

    private class TweetGetterTask extends AsyncTask<String, Void, String> {
        public static final String TAG = "twitteringRoombaLog";

        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            Log.d(TAG, "Connecting");
            HttpURLConnection urlConnection;
            try {
                urlConnection = (HttpURLConnection) new URL(urls[0]).openConnection();
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
                    JsonElement element = parser.parse(String.valueOf(reader));
                    Log.d(TAG, "parsed");
                    Gson gson = new Gson();
                    Tweet p = gson.fromJson(element, Tweet.class);
                    Log.d(TAG, element.toString());
                    return p.toString();
                }
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
                return "Exception";
            }
            return "ERROR";
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            setTweet(result);
        }
    }
}