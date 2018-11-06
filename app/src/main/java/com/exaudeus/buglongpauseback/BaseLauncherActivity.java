package com.exaudeus.buglongpauseback;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public abstract class BaseLauncherActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkNextIntent();
    }

    private void checkNextIntent() {
        PendingIntent newIntent = getNextIntent();
        if (newIntent == null) {
            finish();
            return;
        }
        try {
            newIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
        finish();
    }

    protected boolean isDeepLinked(Uri data) {
        return (DeepLinkContract.getDeepLinkUrls().match(data) != -1);
    }

    protected abstract PendingIntent getNextIntent();
}