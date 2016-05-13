package com.example.abhim_000.twitterBot;

import java.util.ArrayList;

public class Singleton {
    private static Singleton instance = null;

    private String param;
    private String sound;
    private ArrayList<Tweet> tweetsArrayList;

    private Singleton(){
        param = "user=12091291029029210921";
        sound = "00";
        tweetsArrayList = new ArrayList<>();
    }

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public String getString(){
        return this.param;
    }

    public void setString(String value){
        param = value;
    }

    public String getSound(){
        return this.sound;
    }

    public void setSound(String value){
        sound = value;
    }

    public ArrayList<Tweet> getTweetsArrayList() {return tweetsArrayList;}

    public void setTweetsArrayList(ArrayList<Tweet> tweetsArrayList) {this.tweetsArrayList = tweetsArrayList;}
}