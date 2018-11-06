package com.exaudeus.buglongpauseback;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DeepLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri data = getIntent().getData();
        Intent intent = new Intent(this, LauncherActivity.class);
        intent.putExtra(DeepLinkContract.IS_DEEP_LINK, true);
        intent.setData(data);
        finish();
        startActivity(intent);
    }

}
