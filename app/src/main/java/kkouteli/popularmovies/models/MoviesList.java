// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies.models;

import java.util.ArrayList;

/**
 * MoviesList represents a list of movies returned by an API call, such as the
 * implemented "Popular" and "Top rated" requests.
 */
public final class MoviesList {
    private int mPage;
    private int mTotalPages;
    private int mTotalResults;
    private ArrayList<Movie> mMovies;

    public MoviesList() {
        mMovies = new ArrayList<>();
    }

    public int getPage() {
        return mPage;
    }
    public MoviesList setPage(int page) {
        mPage = page;
        return this;
    }
    public int getTotalPages() {
        return mTotalPages;
    }
    public MoviesList setTotalPages(int totalPages) {
        mTotalPages = totalPages;
        return this;
    }
    public int getTotalResults() {
        return mTotalResults;
    }
    public MoviesList setTotalResults(int totalResults) {
        mTotalResults = totalResults;
        return this;
    }
    public ArrayList<Movie> getMovies() {
        return mMovies;
    }
    public Movie getMovie(int index) {
        return mMovies.get(index);
    }
    public MoviesList addMovie(Movie movie) {
        mMovies.add(movie);
        return this;
    }
}
