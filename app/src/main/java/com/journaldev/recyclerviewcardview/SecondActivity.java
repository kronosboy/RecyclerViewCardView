package com.journaldev.recyclerviewcardview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by antonio on 10/11/2018.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("SECOND_ACTIVITY", "on stop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("SECOND_ACTIVITY", "onDestroy");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("SECOND_ACTIVITY", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("SECOND_ACTIVITY", "onPause");
    }
}
