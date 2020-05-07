package com.plumillonforge.ewa.presentation.app;

import android.app.Application;

/**
 * Created by Flavien Norindr
 */
public class EWAApp extends Application {
    static EWAApp REF;

    static EWAApp getInstance() {
        return REF;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        REF = this;
    }
}
