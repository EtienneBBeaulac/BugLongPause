package com.exaudeus.buglongpauseback;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.util.ArrayList;

public class LauncherActivity extends BaseLauncherActivity {

    private static final String ARC_DEVICE_PATTERN = ".+_cheets|cheets_.+";
    private static final String CHROMIUM_BRAND_MANUFACTURER = "chromium";

    @Override
    public PendingIntent getNextIntent() {
        PendingIntent pendingIntent;

        Uri data = getIntent().getData();
        boolean isDeepLinkHandled;
        if (data != null && !data.toString().isEmpty()) {
            //Deep Linking
            if (isDeepLinked(data)) {
                isDeepLinkHandled = startDeepLinkActivities(DeepLinkUtil.parseDeepLink(data, this));
                if (isDeepLinkHandled) {
                    return null;
                }
            }

        }
        if (data != null && getIntent().getBooleanExtra(DeepLinkContract.IS_DEEP_LINK, false) && !isChromebook()) { // If the deep link is not supported, open in browser
            openInBrowser(data);
            return null;
        }
        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && isInMultiWindowMode()) {
            intent = MainActivity.createNewScreenIntent(getApplicationContext());
            finishAndRemoveTask();
        } else {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        return pendingIntent;
    }

    private boolean startDeepLinkActivities(ArrayList<Intent> intents) {
        if (intents == null) {
            return false;
        }
        finish();
        if (intents.isEmpty()) {
            return true;
        }
        startActivities(intents.toArray(new Intent[0]));
        return true;
    }

    private boolean isChromebook() {
        return (Build.DEVICE != null && Build.DEVICE.matches(ARC_DEVICE_PATTERN)
                || (Build.BRAND != null && Build.BRAND.equals(CHROMIUM_BRAND_MANUFACTURER))
                || (Build.MANUFACTURER != null && Build.MANUFACTURER.equals(CHROMIUM_BRAND_MANUFACTURER)));
    }

    private void openInBrowser(Uri data) {
        Intent defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER);
        defaultBrowser.setData(data);
        try {
            startActivity(defaultBrowser);
        } catch (ActivityNotFoundException ignored) {

        }
    }
}
