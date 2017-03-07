// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies.models;

/**
 * This class is used to hold data for a specific movie as returned from "The Movie DB"
 * Movie contains only a minimal set of fields returned by all APIs used in this application
 */
public final class Movie {

    private long mId;
    private String mTitle;
    private String mOverview;
    private String mPosterPath;
    private String mBackdropPath;
    private String mOriginalTitle;
    private double mPopularity;
    private double mVoteAverage;
    private long mVoteCount;

    public long getId() {
        return mId;
    }

    public Movie setId(long id) {
        mId = id;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public Movie setTitle(String title) {
        mTitle = title;
        return this;
    }

    public String getOverview() {
        return mOverview;
    }

    public Movie setOverview(String overview) {
        mOverview = overview;
        return this;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public Movie setPosterPath(String posterPath) {
        mPosterPath = posterPath;
        return this;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public Movie setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
        return this;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public Movie setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
        return this;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public Movie setPopularity(double popularity) {
        mPopularity = popularity;
        return this;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public Movie setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
        return this;
    }

    public long getVoteCount() {
        return mVoteCount;
    }

    public Movie setVoteCount(long voteCount) {
        mVoteCount = voteCount;
        return this;
    }
}
