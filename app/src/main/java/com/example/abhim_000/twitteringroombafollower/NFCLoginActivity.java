package com.example.abhim_000.twitteringroombafollower;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NFCLoginActivity extends AppCompatActivity {

/*    got help from this tutorial: http://code.tutsplus.com/tutorials/reading-nfc-tags-with-android--mobile-17278
      also referred to: https://www.youtube.com/watch?v=9HbuHlsoDQc
 */

    public static final String TAG = "twitteringRoombaLog";
    private EditText txtText;
    private Button button;
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfclogin);
        this.nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        this.button = (Button) findViewById(R.id.button2);
        this.txtText = (EditText) findViewById(R.id.editText2);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkText(txtText.getText().toString())) {
                        Intent intent = new Intent(getApplicationContext(), TweetFeedActivity.class);
                        startActivity(intent);
                        Log.d(TAG, "correct");
                    } else {
                        Log.d(TAG, "wrong");
                        Toast.makeText(getApplicationContext(), "wrong credentials", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage().toString());
                }
            }
        });
    }

    private boolean checkText(String s) {
        return true;
    }

    private void handleIntent(Intent intent) {
        Toast.makeText(getApplicationContext(), "Read from tag", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "new intent");
        handleIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        Intent intent = new Intent(this, NFCLoginActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, 0);
        IntentFilter[] intentFilter = new IntentFilter[] {};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter,null);
        super.onResume();
    }

    @Override
    protected void onPause() {
        nfcAdapter.disableForegroundDispatch(this);
        super.onPause();
    }
}