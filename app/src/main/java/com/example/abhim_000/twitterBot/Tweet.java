package com.example.abhim_000.twitterBot;

public class Tweet {
//    public String postId;
//    public String id;
    public String name;
//    public String email;
    public String body;
//    public String toString(){
//        return "postId: " + postId +
//               "id: " + id +
//               "name: " + name +
//               "email: " + email +
//               "body: " + body;
//    }

    public Tweet(String name, String body){
        this.name = name;
        this.body = body;
    }
}