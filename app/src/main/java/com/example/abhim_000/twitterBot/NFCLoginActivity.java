package com.example.abhim_000.twitterBot;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class NFCLoginActivity extends AppCompatActivity {
    /*
        got help from this tutorial: http://code.tutsplus.com/tutorials/reading-nfc-tags-with-android--mobile-17278
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
                        Intent intent = new Intent(getApplicationContext(), SearchParamActivity.class);
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
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String[] techList = tag.getTechList();
        String searchedTech = Ndef.class.getName();
        String searchMifare = MifareUltralight.class.getName();

        for (String tech : techList) {
            if (searchMifare.equals(tech)){
                Log.d(TAG,"Mifare");
            }
            else if (searchedTech.equals(tech)) {
                Log.d(TAG, "Executing task");
                new NdefReaderTask().execute(tag);
                break;
            }
        }
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

    /**
     * Background task for reading the data. Do not block the UI thread while reading.
     *
     * @author Ralf Wondratschek
     *
     */
    private class NdefReaderTask extends AsyncTask<Tag, Void, String> {

        @Override
        protected String doInBackground(Tag... params) {
            Log.d(TAG, "doing in background");
            Tag tag = params[0];

            Ndef ndef = Ndef.get(tag);
            if (ndef == null) {
                // NDEF is not supported by this Tag.
                Log.d(TAG, "null");
                return null;
            }

            NdefMessage ndefMessage = ndef.getCachedNdefMessage();

            NdefRecord[] records = ndefMessage.getRecords();
            for (NdefRecord ndefRecord : records) {
                if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                    try {
                        Log.d(TAG, "reading");
                        return readText(ndefRecord);
                    } catch (UnsupportedEncodingException e) {
                        Log.e(TAG, "Unsupported Encoding", e);
                    }
                }
            }
            return null;
        }

        private String readText(NdefRecord record) throws UnsupportedEncodingException {
        /*
         * See NFC forum specification for "Text Record Type Definition" at 3.2.1
         *
         * http://www.nfc-forum.org/specs/
         *
         * bit_7 defines encoding
         * bit_6 reserved for future use, must be 0
         * bit_5..0 length of IANA language code
         */

            byte[] payload = record.getPayload();

            // Get the Text Encoding
            String textEncoding;
            if ((payload[0] & 128) == 0) textEncoding = "UTF-8";
            else textEncoding = "UTF-16";

            // Get the Language Code
            int languageCodeLength = payload[0] & 0063;

            Log.d(TAG, "returning text");
            return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Log.d(TAG, "setting text");
                txtText.setText("Read content: " + result);
            }
        }
    }
}