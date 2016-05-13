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

    // Used to quickly load the next tweetString elements onto the list view
    private static class ViewHolder {
        TextView text;
        TextView date;
        TextView screen_name;
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
            viewHolder.text = (TextView) convertView.findViewById(R.id.tweetString);
            viewHolder.date = (TextView) convertView.findViewById(R.id.dateString);
            viewHolder.screen_name = (TextView) convertView.findViewById(R.id.nameString);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(tweet.getTweetText());
        viewHolder.date.setText(tweet.getTweetDate());
        viewHolder.screen_name.setText(tweet.getTweetScreenName());
        return convertView;
    }
}