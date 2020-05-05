package com.ostrov.musicalstructureapp;

import java.io.Serializable;
import java.util.ArrayList;

class Artist implements Serializable, Comparable<Artist> {
    private String title;
    private ArrayList<Album> albums;

    /**
     * Constructor
     * @param title artist name
     */
    Artist(String title) {
        this.title = title;
        this.albums = new ArrayList<>();
    }

    /**
     * Get name of artist
     */
    String getTitle() {
        return title;
    }

    /**
     * Get list of albums
     */
    ArrayList<Album> getAlbums() {
        return albums;
    }

    /**
     * Add new album in list of albums
     */
    void setAlbum(Album album) {
        this.albums.add(album);
    }

    @Override
    public int compareTo(Artist artist) {
        if (!this.title.equals(artist.getTitle()))
            return 1;
        return 0;
    }
}
