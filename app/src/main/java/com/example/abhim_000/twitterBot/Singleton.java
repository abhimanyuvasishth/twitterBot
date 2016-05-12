package com.example.abhim_000.twitterBot;

public class Singleton {
    private static Singleton mInstance = null;

    private String param;
    private String sound;

    private Singleton(){
        param = "user=12091291029029210921";
        sound = "00";
    }

    public static Singleton getInstance(){
        if(mInstance == null){
            mInstance = new Singleton();
        }
        return mInstance;
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
}
