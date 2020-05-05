package com.ostrov.musicalstructureapp;

import java.io.Serializable;

class Song implements Serializable, Comparable<Song> {
    private String title;
    private Album album;

    /**
     * Constructor
     * @param title song title
     * @param album of song
     */
    Song(String title, Album album) {
        this.title = title;
        this.album = album;

        album.setSong(this);
    }

    /**
     * Get title of song
     */
    String getTitle() {
        return title;
    }

    /**
     * Get Album object
     */
    Album getAlbum() {
        return album;
    }

    @Override
    public int compareTo(Song song) {
        if (!this.title.equals(song.title))
            return 1;
        return this.album.compareTo(song.getAlbum());
    }
}
