// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import kkouteli.popularmovies.models.Movie;
import kkouteli.popularmovies.utilities.MovieDbApi;
import kkouteli.popularmovies.utilities.MovieDbApiException;

/**
 * MoviesAdapter
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private ArrayList<Movie> mMovieList;

    public MoviesAdapter() {
        mMovieList = null;
    }

    /**
     * Updates the list of movies to display
     * @param movieList Array list of movies
     */
    public void setMovieList(ArrayList<Movie> movieList) {
        mMovieList = movieList;
        this.notifyDataSetChanged();
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_poster_item, parent, false);
        MovieViewHolder holder = new MovieViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView mPosterImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mPosterImageView = (ImageView) itemView.findViewById(R.id.iv_poster);
        }

        public void bind(Movie movie) {
            try {
                URL url = MovieDbApi.getMovieImageUrl(movie.getPosterPath());
                Picasso.with(mPosterImageView.getContext())
                        .load(url.toString())
                        .into(mPosterImageView);
            } catch (MovieDbApiException me) {
            }
        }

    }
}
