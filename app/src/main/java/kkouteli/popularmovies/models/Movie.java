// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class is used to hold data for a specific movie as returned from "The Movie DB"
 * Movie contains only a minimal set of fields returned by all APIs used in this application
 */
public final class Movie implements Parcelable {

    private long mId;
    private String mTitle;
    private String mOverview;
    private String mPosterPath;
    private String mBackdropPath;
    private String mOriginalTitle;
    private String mReleaseDate;
    private double mPopularity;
    private double mVoteAverage;
    private long mVoteCount;

    public static final String TAG_ID               = "id";
    public static final String TAG_TITLE            = "title";
    public static final String TAG_OVERVIEW         = "overview";
    public static final String TAG_POSTER_PATH      = "poster_path";
    public static final String TAG_BACKDROP_PATH    = "backdrop_path";
    public static final String TAG_ORIGINAL_TITLE   = "original_title";
    public static final String TAG_RELEASE_DATE     = "release_date";
    public static final String TAG_POPULARITY       = "popularity";
    public static final String TAG_VOTE_AVERAGE     = "vote_average";
    public static final String TAG_VOTE_COUNT       = "vote_count";

    public static final String MOVIE_EXTRA_NAME = "movie";

    public Movie() {}

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

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public Movie setReleaseDate(String dateString) {
        mReleaseDate = dateString;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mTitle);
        dest.writeString(mOverview);
        dest.writeString(mPosterPath);
        dest.writeString(mBackdropPath);
        dest.writeString(mOriginalTitle);
        dest.writeString(mReleaseDate);
        dest.writeDouble(mPopularity);
        dest.writeDouble(mVoteAverage);
        dest.writeLong(mVoteCount);
    }

    public static final Parcelable.Creator<Movie> CREATOR =
            new Parcelable.Creator<Movie>() {

                @Override
                public Movie createFromParcel(Parcel source) {
                    return new Movie(source);
                }

                @Override
                public Movie[] newArray(int size) {
                    return new Movie[size];
                }
            };

    private Movie(Parcel parcel) {
        mId = parcel.readLong();
        mTitle = parcel.readString();
        mOverview = parcel.readString();
        mPosterPath = parcel.readString();
        mBackdropPath = parcel.readString();
        mOriginalTitle = parcel.readString();
        mReleaseDate = parcel.readString();
        mPopularity = parcel.readDouble();
        mVoteAverage = parcel.readDouble();
        mVoteCount = parcel.readLong();
    }
}
