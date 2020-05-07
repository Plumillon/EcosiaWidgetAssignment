package com.plumillonforge.ewa.presentation.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;

/**
 * Created by Flavien Norindr
 */
public class PermissionsChecker {
    public static void openSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.parse("package:" + activity.getApplicationContext().getPackageName());
        intent.setData(uri);
        activity.startActivity(intent);
    }

    public static void askPermission(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(
                activity,
                permissions,
                requestCode
        );
    }
}
