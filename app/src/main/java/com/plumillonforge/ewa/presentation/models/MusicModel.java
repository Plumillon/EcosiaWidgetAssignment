package com.plumillonforge.ewa.presentation.models;

import android.net.Uri;

public class MusicModel {
    public String title;
    public String artist;
    public Long duration;
    public Uri uri;
    public String name;

    public MusicModel(String title, String artist, Long duration, String path, String name) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.uri = Uri.parse(path);
        this.name = name;
    }
}
