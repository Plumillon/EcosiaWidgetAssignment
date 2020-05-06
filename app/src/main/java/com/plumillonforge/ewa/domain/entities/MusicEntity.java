package com.plumillonforge.ewa.domain.entities;

public class MusicEntity {
    public String id;
    public String title;
    public String artist;
    public Long duration;
    public String path;
    public String name;

    public MusicEntity(String id, String title, String artist, Long duration, String path, String name) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.path = path;
        this.name = name;
    }
}
