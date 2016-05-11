package com.example.abhim_000.twitterBot;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchParamActivity extends AppCompatActivity {
    private EditText txtText;
    public static final String TAG = "twitteringRoombaLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_param);

        txtText = (EditText) findViewById(R.id.search);
        Button hashtag = (Button) findViewById(R.id.hashtag);
        Button at = (Button) findViewById(R.id.at);
        hashtag.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String toSpeak = txtText.getText().toString();
                if (toSpeak.length() == 0){
                    Toast.makeText(getApplicationContext(), "Enter a search parameter! " + toSpeak, Toast.LENGTH_LONG).show();
                }
                else {
                    toSpeak = "tag="+toSpeak;
                    Log.d(TAG, "Clicked hashtag");
                    Intent intent = new Intent(getApplicationContext(), TweetFeedActivity.class);
                    intent.putExtra("message", toSpeak);
                    Singleton.getInstance().setString(toSpeak);
                    startActivity(intent);
                }
            }
        });

        at.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String toSpeak = txtText.getText().toString();
                if (toSpeak.length() == 0){
                    Toast.makeText(getApplicationContext(), "Enter a search parameter!" + toSpeak, Toast.LENGTH_LONG).show();
                }
                else {
                    toSpeak = "user="+toSpeak;
                    Log.d(TAG, "Clicked hashtag");
                    Intent intent = new Intent(getApplicationContext(), TweetFeedActivity.class);
                    intent.putExtra("message", toSpeak);
                    Singleton.getInstance().setString(toSpeak);
                    startActivity(intent);
                }
            }
        });
    }
}
