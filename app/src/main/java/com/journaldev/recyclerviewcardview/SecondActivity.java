package com.journaldev.recyclerviewcardview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by antonio on 10/11/2018.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String myVar = getIntent().getStringExtra("myVar");
        Boolean myBool = getIntent().getBooleanExtra("myBool", true);

        TextView displayVariable = (TextView) findViewById(R.id.myTxt);
        TextView displayBool = (TextView) findViewById(R.id.myBool);
        displayVariable.setText(myVar);
        if(myBool) {
            displayBool.setText("myBool variable is True");
        } else {
            displayBool.setText("myBool variable is False");
        }

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
