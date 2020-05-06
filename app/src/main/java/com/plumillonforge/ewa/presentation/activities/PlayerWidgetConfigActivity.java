package com.plumillonforge.ewa.presentation.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.plumillonforge.ewa.R;
import com.plumillonforge.ewa.presentation.app.DependenciesProvider;
import com.plumillonforge.ewa.presentation.app.PermissionsChecker;
import com.plumillonforge.ewa.presentation.models.MusicModel;
import com.plumillonforge.ewa.presentation.viewModels.PlayerWidgetViewModel;
import com.plumillonforge.ewa.presentation.viewModels.ViewModelFactory;

public class PlayerWidgetConfigActivity extends FragmentActivity {
    private TextView textMusic;
    private Button buttonLoad;
    private ViewGroup layoutPermission;
    private Button buttonSettings;
    private ViewModelFactory viewModelFactory = DependenciesProvider.providesViewModelFactory();
    private PlayerWidgetViewModel viewModel;
    public static final int RUNTIME_PERMISSION_CODE = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_widget_config_activity);
        textMusic = findViewById(R.id.textMusic);
        buttonLoad = findViewById(R.id.buttonLoad);
        layoutPermission = findViewById(R.id.layoutPermission);
        buttonSettings = findViewById(R.id.buttonSettings);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlayerWidgetViewModel.class);

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        checkRuntimePermission();
        load();
    }

    private void load() {
        viewModel.load();
        viewModel.getMusic().observe(this, new Observer<MusicModel>() {
            @Override
            public void onChanged(MusicModel music) {
                textMusic.setText(music != null ? music.title + " " + music.artist + " " + music.duration : "");
            }
        });
        viewModel.isPermissionGranted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean granted) {
                layoutPermission.setVisibility(granted ? View.GONE : View.VISIBLE);
            }
        });
    }

    private void openSettings() {
        PermissionsChecker.openSettings(this);
    }

    public void checkRuntimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                PermissionsChecker.askPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, RUNTIME_PERMISSION_CODE);
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
