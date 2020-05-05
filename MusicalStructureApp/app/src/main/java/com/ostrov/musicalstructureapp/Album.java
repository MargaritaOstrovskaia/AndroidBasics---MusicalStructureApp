package com.ostrov.musicalstructureapp;

import java.io.Serializable;
import java.util.ArrayList;

class Album implements Serializable, Comparable<Album> {
    private String title;
    private int year;
    private Artist artist;
    private ArrayList<Song> songs;

    /**
     * Constructor
     * @param title of album
     * @param year of album
     * @param artist of album
     */
    Album(String title, int year, Artist artist) {
        this.title = title;
        this.year = year;
        this.artist = artist;
        this.songs = new ArrayList<>();

        artist.setAlbum(this);
    }

    /**
     * Add new song
     * @param song object
     */
    void setSong(Song song) {
        if (!this.songs.contains(song))
            this.songs.add(song);
    }

    /**
     * Get album title
     */
    String getTitle() {
        return title;
    }

    /**
     * Get album year
     */
    int getYear() {
        return year;
    }

    /**
     * Get album artist
     */
    Artist getArtist() {
        return artist;
    }

    /**
     * Get list of songs
     */
    ArrayList<Song> getSongs() {
        return songs;
    }

    @Override
    public int compareTo(Album album) {
        if (!this.title.equals(album.getTitle()))
            return 1;
        if (this.year != album.getYear())
            return 1;
        return this.artist.compareTo(album.getArtist());
    }
}
