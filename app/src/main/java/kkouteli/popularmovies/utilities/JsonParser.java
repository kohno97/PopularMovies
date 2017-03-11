// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies.utilities;

import kkouteli.popularmovies.models.Movie;
import kkouteli.popularmovies.models.MoviesList;
import kkouteli.popularmovies.models.ApiResponse;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class JsonParser {

    private JSONObject mObject;

    public JsonParser(String body) throws JSONException {
        mObject = new JSONObject(body);
    }

    public ApiResponse getApiResponse() throws JSONException {
        return new ApiResponse(
                mObject.getString(ApiResponse.TAG_STATUS_MESSAGE),
                mObject.getInt(ApiResponse.TAG_STATUS_CODE),
                mObject.getBoolean(ApiResponse.TAG_SUCCESS)
        );
    }

    public Movie getMovie() throws JSONException {
        return getMovie(mObject);
    }

    public Movie getMovie(JSONObject object) throws JSONException {
        return (new Movie())
                .setId(object.getInt(Movie.TAG_ID))
                .setTitle(object.getString(Movie.TAG_TITLE))
                .setOriginalTitle(object.getString(Movie.TAG_ORIGINAL_TITLE))
                .setOverview(object.getString(Movie.TAG_ORIGINAL_TITLE))
                .setBackdropPath(object.getString(Movie.TAG_BACKDROP_PATH))
                .setPosterPath(object.getString(Movie.TAG_POSTER_PATH))
                .setPopularity(object.getDouble(Movie.TAG_POPULARITY))
                .setVoteAverage(object.getDouble(Movie.TAG_VOTE_AVERAGE))
                .setVoteCount(object.getInt(Movie.TAG_VOTE_COUNT));
    }

    public MoviesList getMoviesList() throws JSONException {
        MoviesList list = (new MoviesList())
                .setPage(mObject.getInt(MoviesList.TAG_PAGE))
                .setTotalPages(mObject.getInt(MoviesList.TAG_TOTAL_PAGES))
                .setTotalResults(mObject.getInt(MoviesList.TAG_TOTAL_RESULTS));
        JSONArray moviesArray = mObject.getJSONArray(MoviesList.TAG_RESULTS);
        for (int i = 0; i < moviesArray.length(); ++i) {
            JSONObject movieObject = moviesArray.getJSONObject(i);
            Movie movie = getMovie(movieObject);
            list.addMovie(movie);
        }
        return list;
    }
}
