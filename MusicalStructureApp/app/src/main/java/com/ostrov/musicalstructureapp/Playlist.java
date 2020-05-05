package com.ostrov.musicalstructureapp;

import java.io.Serializable;
import java.util.ArrayList;

class Playlist implements Serializable {
    private ArrayList<Song> songs;
    private int currSongPosition;
    private Parent parent;
    private Actions status;

    /**
     * Constructor
     */
    Playlist(Parent parent) {
        this.parent = parent;
        this.songs = new ArrayList<>();
        this.currSongPosition = -1;
        this.status = Actions.SHOW_EQUALIZER_PLAY;
    }

    /**
     * Get Song object
     */
    ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Add songs to list of songs
     * @param songs array list of songs
     */
    void setSongs(ArrayList<Song> songs) {
        this.songs.addAll(songs);
    }

    /**
     * Get the position of the selected song
     */
    int getCurrSongPosition() {
        return currSongPosition;
    }

    /**
     * Set the position of the selected song
     * and set default view status
     */
    void setCurrSongPosition(int currSongPosition) {
        this.currSongPosition = currSongPosition;
        status = Actions.SHOW_PLAY_BTN;
    }

    /**
     * Get view status
     */
    Actions getStatus() {
        return status;
    }

    /**
     * Set view status
     */
    void setStatus(Actions status) {
        this.status = status;
    }

    /**
     * Switch view status
     */
    void changeStatus() {
        if (this.status == Actions.SHOW_EQUALIZER_PLAY)
            this.status = Actions.SHOW_EQUALIZER_STOP;
        else
            this.status = Actions.SHOW_EQUALIZER_PLAY;
    }

    /**
     * Get parent
     */
    public Parent getParent() {
        return parent;
    }
}
