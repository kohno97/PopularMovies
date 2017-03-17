package kkouteli.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;

import kkouteli.popularmovies.models.Movie;
import kkouteli.popularmovies.utilities.MovieDbApi;
import kkouteli.popularmovies.utilities.MovieDbApiException;

public class MovieDetailsActivity extends AppCompatActivity {

    private Movie mMovie;

    private ImageView mBackdrop;
    private ImageView mPoster;
    private TextView mOriginalTitle;
    private TextView mReleaseDate;
    private TextView mOverview;
    private RatingBar mRating;
    private TextView mRatingDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mBackdrop       = (ImageView) findViewById(R.id.iv_backdrop);
        mPoster         = (ImageView) findViewById(R.id.iv_details_poster);
        mOriginalTitle  = (TextView)  findViewById(R.id.tv_original_title);
        mReleaseDate    = (TextView)  findViewById(R.id.tv_release_date);
        mOverview       = (TextView)  findViewById(R.id.tv_overview);
        mRating         = (RatingBar) findViewById(R.id.rb_rating);
        mRatingDetails  = (TextView)  findViewById(R.id.tv_rating_details);

        Intent intent = getIntent();
        if (savedInstanceState != null) {
            mMovie = savedInstanceState.getParcelable(Movie.MOVIE_EXTRA_NAME);

        } else if (intent.hasExtra(Movie.MOVIE_EXTRA_NAME)) {
            mMovie = getIntent().getParcelableExtra(Movie.MOVIE_EXTRA_NAME);
        } else {
            mMovie = null;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMovie = savedInstanceState.getParcelable(Movie.MOVIE_EXTRA_NAME);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Movie.MOVIE_EXTRA_NAME, mMovie);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMovie != null) {
            setTitle(mMovie.getTitle());
            mOriginalTitle.setText(mMovie.getOriginalTitle());
            mReleaseDate.setText(mMovie.getReleaseDate());
            mOverview.setText(mMovie.getOverview());
            mRating.setRating((float)mMovie.getVoteAverage());
            mRatingDetails.setText(String.format(
                    getString(R.string.movie_rating_details),
                    mMovie.getVoteAverage(),
                    mMovie.getVoteCount()));
            try {
                URL backdropUrl = MovieDbApi.getMovieImageUrl(
                        mMovie.getBackdropPath(),
                        MovieDbApi.IMAGE_SIZE_PATH_500);
                Picasso.with(this)
                        .load(backdropUrl.toString())
                        .into(mBackdrop);
                URL posterUrl   = MovieDbApi.getMovieImageUrl(
                        mMovie.getPosterPath(),
                        MovieDbApi.IMAGE_SIZE_PATH_185
                );
                Picasso.with(this)
                        .load(posterUrl.toString())
                        .into(mPoster);
            } catch (MovieDbApiException me) {
                // nothing?
            }
        }
    }
}
