package com.example.abhim_000.twitterBot;

public class ApiCredentials{
    private static final String API_URL = "http://twitteringroomba.herokuapp.com/";
    private String params = "";
    public void setParams(String params) {
        this.params = params;
    }
    public String getApiUrl() {
        return API_URL + params;
    }
}