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

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.RVHolder> {
    public interface OnItemClickListener {
        void onItemClick(Artist item);
    }
    private final OnItemClickListener listener;
    private Context context;
    private ArrayList<Artist> list;

    ArtistAdapter(Context context, @NonNull ArrayList<Artist> list, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public ArtistAdapter.RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.RVHolder holder, int position) {
        // set Artist title
        Artist artist = list.get(position);
        holder.textViewTitle.setText(artist.getTitle());

        // set Artist image
        String name = artist.getTitle().replaceAll(" ", "_").toLowerCase();
        Drawable drawable = context.getDrawable(context.getResources().getIdentifier(name, context.getString(R.string.def_type),
                context.getPackageName()));
        holder.imageView.setImageDrawable(drawable);

        // set listeners on views
        holder.imageView.setOnClickListener(view -> listener.onItemClick(artist));
        holder.textViewTitle.setOnClickListener(view -> listener.onItemClick(artist));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class RVHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;

        RVHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_img);
            textViewTitle = itemView.findViewById(R.id.item_title);
        }
    }
}
