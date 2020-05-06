package com.plumillonforge.ewa.presentation.activities;

import android.Manifest;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.plumillonforge.ewa.R;
import com.plumillonforge.ewa.presentation.app.DependenciesProvider;
import com.plumillonforge.ewa.presentation.app.PermissionsChecker;
import com.plumillonforge.ewa.presentation.viewModels.PlayerWidgetConfigViewModel;
import com.plumillonforge.ewa.presentation.viewModels.ViewModelFactory;

public class PlayerWidgetConfigActivity extends FragmentActivity {
    private ViewGroup layoutPermission;
    private Button buttonSettings;
    private ViewModelFactory viewModelFactory = DependenciesProvider.providesViewModelFactory();
    private PlayerWidgetConfigViewModel viewModel;
    private int appWidgetId;
    public static final int RUNTIME_PERMISSION_CODE = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        setContentView(R.layout.player_widget_config_activity);
        layoutPermission = findViewById(R.id.layoutPermission);
        buttonSettings = findViewById(R.id.buttonSettings);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlayerWidgetConfigViewModel.class);

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        checkRuntimePermission();
        observe();
    }

    private void observe() {
        viewModel.isPermissionGranted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean granted) {
                layoutPermission.setVisibility(granted ? View.GONE : View.VISIBLE);

                if (granted) {
                    updateWidget();
                }
            }
        });
    }

    private void updateWidget() {
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

    private void openSettings() {
        PermissionsChecker.openSettings(this);
    }

    public void checkRuntimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionResult = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                PermissionsChecker.askPermission(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                }, RUNTIME_PERMISSION_CODE);
            } else {
                updateWidget();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RUNTIME_PERMISSION_CODE) {
            viewModel.setPermissionGranted(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);
        }
    }
}
