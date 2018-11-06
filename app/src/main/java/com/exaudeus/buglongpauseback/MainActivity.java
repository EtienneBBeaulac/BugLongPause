package com.exaudeus.buglongpauseback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String TAG;

    public static Intent createStandardIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @SuppressLint("InlinedApi")
    public static Intent createNewScreenIntent(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        return starter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.textView);
        if (intent.getBooleanExtra(DeepLinkContract.IS_DEEP_LINK, false)) {
            textView.setText(R.string.next_activity);
            TAG = "BUG_NEXT_ACTIVITY";
        } else {
            textView.setText(R.string.previous_activity);
            TAG = "BUG_PREVIOUS_ACTIVITY";
        }
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: start");
        super.onStart();
        Log.d(TAG, "onStart: end");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: start");
        super.onStop();
        Log.d(TAG, "onStop: end");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: start");
        super.onDestroy();
        Log.d(TAG, "onDestroy: end");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: start");
        super.onResume();
        Log.d(TAG, "onResume: end");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: start");
        super.onPause();
        Log.d(TAG, "onPause: end");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: start");
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: end");
    }
}
