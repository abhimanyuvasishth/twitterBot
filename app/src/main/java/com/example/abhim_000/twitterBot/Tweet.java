package com.example.abhim_000.twitterBot;

public class Tweet {
    public String tweet;
    public String date;
    public String toString(){
        return "text: " + tweet +
               "date: " + date;
    }

    public String getTweetText(){
        return this.tweet;
    }

    public String getDateText(){
        return this.date;
    }

    public Tweet(String tweet, String date){
        this.tweet = tweet;
        this.date = date;
    }
}