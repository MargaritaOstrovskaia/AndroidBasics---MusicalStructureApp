package com.ostrov.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ostrov.musicalstructureapp.databinding.ActivityPlaylistBinding;

public class PlaylistActivity extends AppCompatActivity {
    private Playlist playlist;
    private PlaylistAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        menu.findItem(R.id.menu_artists).setVisible(true);
        if (playlist.getParent() == Parent.ALBUM)
            menu.findItem(R.id.menu_albums).setVisible(true);
        else
            menu.findItem(R.id.menu_albums).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_artists:
                Intent artistIntent = new Intent(PlaylistActivity.this, ArtistActivity.class);
                startActivity(artistIntent);
                break;
            case R.id.menu_albums:
                Intent albumIntent = new Intent(PlaylistActivity.this, AlbumActivity.class);
                albumIntent.putExtra(getString(R.string.artists), playlist.getSongs().get(0).getAlbum().getArtist());
                startActivity(albumIntent);
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPlaylistBinding binding = ActivityPlaylistBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        playlist = (Playlist) intent.getSerializableExtra(getString(R.string.playlist));
        if (playlist != null) {
            PlaylistAdapter.OnItemClickListener listener = (action, currPosition) -> {
                if (action == Actions.OPEN_ALBUMS)
                    finish();
                else
                    changeEqualizer(action, currPosition);
            };

            adapter = new PlaylistAdapter(this, playlist, listener);
            binding.rvSongs.setAdapter(adapter);

            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            binding.rvSongs.setLayoutManager(manager);
        }
    }

    /**
     * Change equalizer state
     * @param action current action
     * @param currPosition current song position
     */
    private void changeEqualizer(Actions action, int currPosition) {
        // get current song title
        String title = playlist.getSongs().get(currPosition).getTitle();
        // change equalizer state and refresh item view
        if (action == Actions.SHOW_EQUALIZER) {
            playlist.changeStatus();
            adapter.notifyItemChanged(currPosition);

            Toast.makeText(PlaylistActivity.this, String.format("Stop %s", title), Toast.LENGTH_SHORT).show();
        }
        else if (action == Actions.SHOW_PLAY_BTN) {
            int lastPosition = playlist.getCurrSongPosition();
            playlist.setCurrSongPosition(currPosition);
            playlist.setStatus(Actions.SHOW_EQUALIZER_PLAY);
            if (lastPosition >= 0)
                adapter.notifyItemChanged(lastPosition);
            adapter.notifyItemChanged(currPosition);

            Toast.makeText(PlaylistActivity.this, String.format("Play %s", title), Toast.LENGTH_SHORT).show();
        }
    }
}
