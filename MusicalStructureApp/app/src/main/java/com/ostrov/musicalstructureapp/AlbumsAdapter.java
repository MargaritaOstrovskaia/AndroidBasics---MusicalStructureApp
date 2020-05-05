package com.ostrov.musicalstructureapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.RVHolder> {
    public interface OnItemClickListener {
        void onItemClick(Album item);
    }
    private final OnItemClickListener listener;
    private Context context;
    private ArrayList<Album> list;

    AlbumsAdapter(Context context, @NonNull ArrayList<Album> list, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public AlbumsAdapter.RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.RVHolder holder, int position) {
        Album album = list.get(position);

        // set Album information
        String year = String.valueOf(album.getYear());
        holder.textViewYear.setText(year);

        String title = album.getTitle();
        holder.textViewTitle.setText(title);

        String artist = album.getArtist().getTitle();
        holder.textViewArtist.setText(artist);

        // set Album image
        String name = String.format("%s_%s", artist, title).replaceAll(" ", "_").toLowerCase();
        Drawable drawable = context.getDrawable(context.getResources().getIdentifier(name, context.getString(R.string.def_type),
                context.getPackageName()));
        holder.imageView.setImageDrawable(drawable);

        // set listeners on views
        holder.imageView.setOnClickListener(view -> listener.onItemClick(album));
        holder.textViewTitle.setOnClickListener(view -> listener.onItemClick(album));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class RVHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewArtist;
        TextView textViewTitle;
        TextView textViewYear;

        RVHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_album_img);
            textViewArtist = itemView.findViewById(R.id.item_album_artist);
            textViewTitle = itemView.findViewById(R.id.item_album_title);
            textViewYear = itemView.findViewById(R.id.item_album_year);
        }
    }
}
