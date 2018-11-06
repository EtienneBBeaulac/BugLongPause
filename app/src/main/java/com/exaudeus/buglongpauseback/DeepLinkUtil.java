package com.exaudeus.buglongpauseback;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.util.ArrayList;

class DeepLinkUtil {

    private final static String LOG_TAG = "Deeplinkutil";

    static ArrayList<Intent> parseDeepLink(Uri uri, Context context) {
        if (uri != null) {
            Log.d(LOG_TAG, "URL: " + uri.toString());
            int match = DeepLinkContract.getDeepLinkUrls().match(uri);

            TaskStackBuilder taskStack = TaskStackBuilder.create(context);
            taskStack.addParentStack(MainActivity.class);
            ArrayList<Intent> intents = new ArrayList<>();
            boolean addedMainIntent = false;
            if (isNewTask(context)) {
                intents.add(MainActivity.createStandardIntent(context));
                addedMainIntent = true;
            }
            switch (match) {
                case DeepLinkContract.TREE:
                    if (!addedMainIntent) {
                        Intent intent = MainActivity.createStandardIntent(context);
                        intent.putExtra(DeepLinkContract.IS_DEEP_LINK, true);
                        intents.add(intent);
                    }
                    break;
                default:
                    Log.d(LOG_TAG, "DEFAULTED");
                    return null;

            }
            return intents;
        }
        return null;
    }


    private static boolean isNewTask(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (activityManager != null && activityManager.getAppTasks() != null && !activityManager.getAppTasks().isEmpty()) {
                ComponentName baseComponent = activityManager.getAppTasks().get(0).getTaskInfo().baseActivity;
                return baseComponent != null && baseComponent.getClassName().equals(LauncherActivity.class.getName());
            }
        }
        return false;
    }

}
