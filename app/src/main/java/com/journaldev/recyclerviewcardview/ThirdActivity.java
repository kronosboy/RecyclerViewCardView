package com.journaldev.recyclerviewcardview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThirdActivity extends AppCompatActivity {

    TextView displayVariable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        displayVariable = (TextView) findViewById(R.id.myTxt);

        Button btcBtn = (Button) findViewById(R.id.btcBtn);
        btcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdActivity.MyAsyncCall myAsyncCall = new ThirdActivity.MyAsyncCall();
                myAsyncCall.execute();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("THIRD_ACTIVITY", "on stop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("THIRD_ACTIVITY", "onDestroy");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("THIRD_ACTIVITY", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("THIRD_ACTIVITY", "onPause");
    }

    class MyAsyncCall extends AsyncTask<String, Void, Void> {

        JSONObject ticker;
        String price;

        @Override
        protected void onPreExecute() {

            displayVariable.setText("Loading today's Bitcoin price...");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Toast.makeText(getApplicationContext(), "Price loaded", Toast.LENGTH_LONG).show();
            displayVariable.setText(price.substring(0,7) + " $");
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String... params) {

            try {

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://api.pro.coinbase.com/products/BTC-USD/ticker")
                        .build();

                Response response = client.newCall(request).execute();
                ticker = new JSONObject(response.body().string());
                price = ticker.getString("price");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
