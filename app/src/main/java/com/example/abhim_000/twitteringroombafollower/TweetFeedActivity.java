package com.example.abhim_000.twitteringroombafollower;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;

// Help for text-speech: http://www.tutorialspoint.com/android/android_text_to_speech.htm

public class TweetFeedActivity extends AppCompatActivity {
    private TweetFeedController controller = new TweetFeedController();
    public static final String TAG = "twitteringRoombaLog";
    private TextToSpeech tts;
    private EditText txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_feed);

        txtText = (EditText) findViewById(R.id.editText);
        Button btnSpeak = (Button) findViewById(R.id.button);

        final TextView Enter = (TextView) findViewById(R.id.textView);
        controller.subscribe(Enter);
        Button ClickButton = (Button) findViewById(R.id.radioButton);
        ClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked");
                controller.getTweets();
            }
        });

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ITALIAN);
                }
            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String toSpeak = txtText.getText().toString();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
}