package com.example.abhim_000.twitterBot;

public class Tweet {
    public String tweet;
    public String date;
    public String screen_name;
    public String toString(){
        return "text: " + tweet +
               "date: " + date +
               "screen_name" + screen_name;
    }

    public String getTweetText(){
        this.tweet = tweet.replaceAll("http?\\S+\\s?", "");
        if (this.tweet.substring(0,2).equals("RT")){
            this.tweet = this.tweet.substring(3,this.tweet.length()-1);
        }
        return this.tweet;
    }

    public String getTweetDate() {
        return this.date;
    }

    public String getTweetScreenName() {
        return this.screen_name;
    }
}