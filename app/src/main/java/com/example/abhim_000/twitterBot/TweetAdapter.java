package com.example.abhim_000.twitterBot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class TweetAdapter extends ArrayAdapter<Tweet>{

    public TweetAdapter(Context context, int resource, ArrayList<Tweet> tweets) {
        super(context, resource, tweets);
    }

    // Used to quickly load the next tweet elements onto the list view
    private static class ViewHolder {
        TextView name;
        TextView body;
    }

    /* Convert view allows you to scroll down a list with a lot of options without pre-loading all the options.
       Only when the view is scrolled, the new options are loaded.
     */
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Tweet tweet = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.tweet_item_layout, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tweetString);
            viewHolder.body = (TextView) convertView.findViewById(R.id.dateString);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(tweet.name);
        viewHolder.body.setText(tweet.body);
        return convertView;
    }
}