package com.upt.cti.cinemabookingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesLVAdapter extends ArrayAdapter<Movie> {

    public MoviesLVAdapter(@NonNull Context context, ArrayList<Movie> moviesList) {
        super(context, 0, moviesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
        }

        Movie movie = getItem(position);

        TextView movieName = listItemView.findViewById(R.id.textViewMovieName);
        ImageView movieImage = listItemView.findViewById(R.id.imageViewMovie);

        movieName.setText(movie.getName());
        Picasso.get().load(movie.getImgUrl()).into(movieImage);

        listItemView.setOnClickListener(v ->
                Toast.makeText(getContext(), "Movie clicked is: " + movie.getName(), Toast.LENGTH_SHORT).show());
        return listItemView;
    }
}
