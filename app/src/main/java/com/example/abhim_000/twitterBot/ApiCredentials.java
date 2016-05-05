package com.example.abhim_000.twitterBot;

public class ApiCredentials{
    private String API_URL = "http://twitteringroomba.herokuapp.com/";
    private String hashtag = "banana";

    public String getApiUrl() {
        return API_URL + hashtag;
    }
}