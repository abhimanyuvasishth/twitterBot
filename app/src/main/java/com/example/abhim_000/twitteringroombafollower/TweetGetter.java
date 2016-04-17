package com.example.abhim_000.twitteringroombafollower;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TweetGetter {
    public static final String tagZZ = "twitteringRoombaLog";

    public String readJsonFromUrl(String url) throws IOException{
        System.out.println(tagZZ+"Reading JSON from a file");
        System.out.println(tagZZ+"----------------------------");
        URLConnection urlConnection =  new URL(url).openConnection();
        urlConnection.connect();
        JsonReader reader = new JsonReader(
        new InputStreamReader(urlConnection.getInputStream()));
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(String.valueOf(reader));
        Gson gson = new Gson();
        Tweet p = gson.fromJson(element, Tweet.class);
        System.out.println(tagZZ+p.toString());
        return p.toString();
    }
}