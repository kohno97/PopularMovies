// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies;

import android.content.Context;
import android.content.Intent;
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
     * @param movieList ArrayList of movies
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

    /**
     * MovieViewHolder is bound to a Movie object. It contains a single ImageView item for
     * displaying the movie's poster image, and a reference to the bound movie.
     *
     * The "on click" handler the holder assigns to the ImageView "puts" the referenced
     * Movie as an extra on the intent used to start the movie details activity in order to
     * pass that information.
     */
    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView mPosterImageView; // Reference to the ImageView used to display the poster
        private Movie mMovie;               // Reference to the bound Movie object.

        /**
         * The constructor initializes the reference to the contained image view, and sets up
         * the click handler for it so that it launches the details activity on click.
         * @param itemView
         */
        public MovieViewHolder(final View itemView) {
            super(itemView);
            mPosterImageView = (ImageView) itemView.findViewById(R.id.iv_poster);
            mPosterImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mMovie) {
                        Intent intent = new Intent(
                                itemView.getContext(),
                                MovieDetailsActivity.class);
                        intent.putExtra(Movie.MOVIE_EXTRA_NAME, mMovie);
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }

        /**
         * Updates the internal reference to the passed Movie object, then updates the
         * poster image view with the poster path of the movie.
         * @param movie Movie object to be bound to
         */
        public void bind(Movie movie) {
            try {
                mMovie = movie;
                URL url = MovieDbApi.getMovieImageUrl(movie.getPosterPath());
                Picasso.with(mPosterImageView.getContext())
                        .load(url.toString())
                        .into(mPosterImageView);
            } catch (MovieDbApiException me) {
            }
        }

    }
}
