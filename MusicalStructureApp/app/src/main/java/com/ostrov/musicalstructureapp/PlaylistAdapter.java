package com.ostrov.musicalstructureapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import es.claucookie.miniequalizerlibrary.EqualizerView;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.RVHolder> {
    public interface OnItemClickListener {
        void onItemClick(Actions actions, int position);
    }
    private final OnItemClickListener listener;
    private Context context;
    private Playlist playlist;

    PlaylistAdapter(Context context, @NonNull Playlist playlist, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.playlist = playlist;
    }

    @NonNull
    @Override
    public PlaylistAdapter.RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.RVHolder holder, int position) {
        // set equalizer state
        if (playlist.getStatus() == Actions.SHOW_EQUALIZER_STOP)
            holder.equalizer.stopBars();
        else
            holder.equalizer.animateBars();

        // set play button and equalizer visibility
        if (position != playlist.getCurrSongPosition()) {
            holder.equalizer.setVisibility(View.GONE);
            holder.imagePlay.setVisibility(View.VISIBLE);
        }
        else {
            holder.equalizer.setVisibility(View.VISIBLE);
            holder.imagePlay.setVisibility(View.GONE);
        }

        // set Title of song
        Song song = playlist.getSongs().get(position);
        holder.textViewSong.setText(song.getTitle());

        // set Artist and Album information
        String album = song.getAlbum().getTitle();
        String artist = song.getAlbum().getArtist().getTitle();
        holder.textViewArtist.setText(String.format("%s - %s", artist, album));

        // set Album image
        String name = String.format("%s_%s", artist, album).replaceAll(" ", "_").toLowerCase();
        Drawable drawable = context.getDrawable(context.getResources().getIdentifier(name, context.getString(R.string.def_type),
                context.getPackageName()));
        holder.imageAlbum.setImageDrawable(drawable);

        // set listeners on views
        holder.imagePlay.setOnClickListener(view -> listener.onItemClick(Actions.SHOW_PLAY_BTN, position));
        holder.equalizer.setOnClickListener(view -> listener.onItemClick(Actions.SHOW_EQUALIZER, position));
        holder.textViewArtist.setOnClickListener(view -> listener.onItemClick(Actions.OPEN_ALBUMS, position));
    }

    @Override
    public int getItemCount() {
        return playlist.getSongs().size();
    }

    static class RVHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView imagePlay;
        ImageView imageAlbum;
        TextView textViewArtist;
        TextView textViewSong;
        EqualizerView equalizer;

        RVHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.item_song);
            imagePlay = itemView.findViewById(R.id.item_song_img_play);
            imageAlbum = itemView.findViewById(R.id.item_song_img_album);
            textViewArtist = itemView.findViewById(R.id.item_song_artist);
            textViewSong = itemView.findViewById(R.id.item_song_title);
            equalizer = itemView.findViewById(R.id.item_song_equalizer);
        }
    }
}
