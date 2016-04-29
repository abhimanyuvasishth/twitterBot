package com.example.abhim_000.twitterBot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TweetFeedActivity extends AppCompatActivity {
    public static final String TAG = "twitteringRoombaLog";
    private ListView listView ; // got help from here; http://androidexample.com/Create_A_Simple_Listview_-_Android_Example/index.php?view=article_discription&aid=65&aaid=90
    private TweetFeedController controller = new TweetFeedController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_feed);

        final TextView Enter = (TextView) findViewById(R.id.jsonText);
        controller.subscribe(Enter);

        Button SpeechButton = (Button) findViewById(R.id.speechSettingsButton);
        SpeechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clickedButton");
                Intent intent = new Intent(getApplicationContext(), SpeechSettingsActivity.class);
                startActivity(intent);
            }
        });

        Button JsonButton = (Button) findViewById(R.id.jsonButton);
        JsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked");
                controller.getTweets();
            }
        });

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.tweetList);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "qwerty qwerty qwerty qwerty qwerty qwerty qwerty qwerty qwerty qwerty qwerty" +
                        "qwerty qwerty qwerty qwerty qwerty qwerty qwerty qwerty qwerty qwerty",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + position + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }
        });
    }
}
