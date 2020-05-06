package com.plumillonforge.ewa.presentation.app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionsChecker {
    private Context context;

    PermissionsChecker(Context context) {
        this.context = context;
    }

    public static void openSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.parse("package:" + activity.getApplicationContext().getPackageName());
        intent.setData(uri);
        activity.startActivity(intent);
    }

    public int checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(context, permission);
    }

    public static void askPermission(Activity activity, String permission, int requestCode) {
        ActivityCompat.requestPermissions(
                activity,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                requestCode
        );
    }
}
