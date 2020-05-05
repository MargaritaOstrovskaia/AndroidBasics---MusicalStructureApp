package com.ostrov.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ostrov.musicalstructureapp.databinding.ActivityAlbumBinding;

public class AlbumActivity extends AppCompatActivity {
    private Artist artist;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_artists).setVisible(true);
        menu.findItem(R.id.menu_albums).setVisible(false);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_artists) {
            Intent newIntent = new Intent(AlbumActivity.this, ArtistActivity.class);
            startActivity(newIntent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAlbumBinding binding = ActivityAlbumBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        artist = (Artist) intent.getSerializableExtra(getString(R.string.artists));
        if (artist != null) {
            AlbumsAdapter adapter = new AlbumsAdapter(getApplicationContext(), artist.getAlbums(), (item) -> {
                // create playlist from selected album and start PlaylistActivity
                Playlist playlist = new Playlist(Parent.ALBUM);
                playlist.setSongs(item.getSongs());
                startNewActivity(playlist);
            });
            binding.rvAlbum.setAdapter(adapter);

            GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),3);
            binding.rvAlbum.setLayoutManager(manager);

            binding.tvAlbumsPlay.setOnClickListener(clickView -> {
                // create playlist from all albums and start PlaylistActivity
                Playlist playlist = getSongsList();
                startNewActivity(playlist);
            });
        }
    }

    /**
     * Create playlist
     * @return playlist with songs
     */
    private Playlist getSongsList() {
        Playlist playlist = new Playlist(Parent.ARTIST);
        for (Album a : artist.getAlbums())
            playlist.setSongs(a.getSongs());

        return playlist;
    }

    /**
     * Start PlaylistActivity
     * @param playlist with songs
     */
    private void startNewActivity(Playlist playlist) {
        Intent newIntent = new Intent(AlbumActivity.this, PlaylistActivity.class);
        newIntent.putExtra(getString(R.string.playlist), playlist);
        startActivity(newIntent);
    }
}
