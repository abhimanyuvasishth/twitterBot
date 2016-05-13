package com.example.abhim_000.twitterBot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchParamActivity extends AppCompatActivity {
    private EditText txtText;
    public static final String TAG = "twitteringRoombaLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_param);

        txtText = (EditText) findViewById(R.id.search);
        txtText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        txtText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    String toSpeak = txtText.getText().toString();
                    if (txtText.getText().toString().substring(0,1).equals("#")){
                        toSpeak = toSpeak.replace("#","").replace("@","").replace(" ","");
                        toSpeak = "tag="+toSpeak;
                    }
                    else if (txtText.getText().toString().substring(0,1).equals("@")){
                        toSpeak = toSpeak.replace("#","").replace("@","").replace(" ","");
                        toSpeak = "user="+toSpeak;
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Start with a # or @", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    Intent intent = new Intent(getApplicationContext(), TweetFeedActivity.class);
                    if (toSpeak.length() == 0){
                        toSpeak = "user=12091291029029210921";
                    }
                    Singleton.getInstance().setString(toSpeak);
                    Singleton.getInstance().setTweetsArrayList(new ArrayList<Tweet>());
                    startActivity(intent);
                }
                return false;
            }
        });
    }
}
