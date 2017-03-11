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
                mObject.getString("status_message"),
                mObject.getInt("status_code"),
                mObject.getBoolean("success")
        );
    }

    public Movie getMovie() throws JSONException {
        return getMovie(mObject);
    }

    public Movie getMovie(JSONObject object) throws JSONException {
        return (new Movie())
                .setId(object.getInt("id"))
                .setTitle(object.getString("title"))
                .setOriginalTitle(object.getString("original_title"))
                .setOverview(object.getString("overview"))
                .setBackdropPath(object.getString("backdrop_path"))
                .setPosterPath(object.getString("poster_path"))
                .setPopularity(object.getDouble("popularity"))
                .setVoteAverage(object.getDouble("vote_average"))
                .setVoteCount(object.getInt("vote_count"));
    }

    public MoviesList getMoviesList() throws JSONException {
        MoviesList list = (new MoviesList())
                .setPage(mObject.getInt("page"))
                .setTotalPages(mObject.getInt("total_pages"))
                .setTotalResults(mObject.getInt("total_results"));
        JSONArray moviesArray = mObject.getJSONArray("results");
        for (int i = 0; i < moviesArray.length(); ++i) {
            JSONObject movieObject = moviesArray.getJSONObject(i);
            Movie movie = getMovie(movieObject);
            list.addMovie(movie);
        }
        return list;
    }
}
