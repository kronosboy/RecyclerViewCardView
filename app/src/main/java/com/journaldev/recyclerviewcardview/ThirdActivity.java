package com.journaldev.recyclerviewcardview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ThirdActivity extends AppCompatActivity {

    private TextView thirdTxt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String response_call = getIntent().getStringExtra("response_text");

        thirdTxt = (TextView) findViewById(R.id.myTxt_third);
        thirdTxt.setText(response_call);

    }

}
