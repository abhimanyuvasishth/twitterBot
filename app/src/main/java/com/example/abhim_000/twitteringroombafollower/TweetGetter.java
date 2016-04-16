package com.example.abhim_000.twitteringroombafollower;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class TweetGetter extends AsyncTask<Void, Void, String> {

    private Exception exception;

    JSONObject latestTweet;

    public JSONObject getLatestTweet() {
        return latestTweet;
    }

    protected void onPreExecute() {
    }

    protected String doInBackground(Void... urls) {
//        String email = emailText.getText().toString();
        // Do some validation here
        ApiCredentials apiCredentials = new ApiCredentials();
        String urlString = apiCredentials.getApiUrl();

        try {
//            URL url = new URL(API_URL + "email=" + email + "&apiKey=" + API_KEY);
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
        try {
            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
            this.latestTweet = object;
//            String requestID = object.getString("requestId");
//            int likelihood = object.getInt("likelihood");
//            JSONArray photos = object.getJSONArray("photos");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}