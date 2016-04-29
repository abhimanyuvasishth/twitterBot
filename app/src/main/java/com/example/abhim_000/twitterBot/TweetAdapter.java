package com.example.abhim_000.twitterBot;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class TweetAdapter extends ArrayAdapter<Tweet>{

    private Context context;
    private int resource;
    private ArrayList<Tweet> objects;

    public TweetAdapter(Context context, int resource, ArrayList<Tweet> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater= ((Activity) context).getLayoutInflater();
        View row=inflater.inflate(resource, parent, false);
        TextView tweetString= (TextView)row.findViewById(R.id.tweetString);
        TextView dateString=(TextView)row.findViewById(R.id.dateString);
        tweetString.setText(objects.get(position).name);
        dateString.setText(objects.get(position).body);
        return row;
    }
}
