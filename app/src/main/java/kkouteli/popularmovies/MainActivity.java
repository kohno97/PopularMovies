// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

import kkouteli.popularmovies.models.ApiResponse;
import kkouteli.popularmovies.models.MoviesList;
import kkouteli.popularmovies.utilities.JsonParser;
import kkouteli.popularmovies.utilities.MovieDbApi;
import kkouteli.popularmovies.utilities.MovieDbApiException;

/**
 * The main activity for this application displays the posters of the most popular
 * or top rated movies in a grid layout. The grid is implemented with a recycler view.
 */
public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "PopularMoviesPreferences";

    private RecyclerView mMoviesRecyclerView;
    private ProgressBar mProgressBar;
    private LinearLayout mMessageLayout;
    private TextView mMessageTitle;
    private TextView mMessageText;

    private MoviesAdapter mMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.pb_waiting);
        mMessageLayout = (LinearLayout) findViewById(R.id.ll_messages);
        mMessageTitle = (TextView) findViewById(R.id.tv_message_title);
        mMessageText = (TextView) findViewById(R.id.tv_message_text);

        mMoviesRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mMoviesRecyclerView.setLayoutManager(gridLayoutManager);

        mMoviesRecyclerView.setHasFixedSize(true);

        mMoviesAdapter = new MoviesAdapter();
        mMoviesRecyclerView.setAdapter(mMoviesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setCheckedItem(item);
        try {
            URL url;
            if (item.getItemId() == R.id.mitem_top_rated) {
                url = MovieDbApi.getTopRatedUrl();
            } else {
                url = MovieDbApi.getPopularUrl();
            }
            makeApiCall(url);
        } catch (MovieDbApiException e) {
            showMessage(
                    getString(R.string.err_api_title),
                    getString(R.string.err_api_text));
        }
        return true;
    }

    private void makeApiCall(URL url) {
        (new ApiCallAsyncTask()).execute(url);
    }

    private void showProgressing() {
        mMessageLayout.setVisibility(View.INVISIBLE);
        mMoviesRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void showPosterGrid(MovieDbApi.Result apiResult) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mMessageLayout.setVisibility(View.INVISIBLE);
        mMoviesRecyclerView.setVisibility(View.VISIBLE);

        try {
            JsonParser parser = new JsonParser(apiResult.getResponseBody());
            MoviesList moviesList = parser.getMoviesList();
            mMoviesAdapter.setMovieList(moviesList.getMovies());
        } catch (JSONException e) {
            showMessage(
                    getString(R.string.err_json_format_title),
                    getString(R.string.err_json_format_text));
        }
    }

    private void setCheckedItem(MenuItem item) {
        item.setChecked(true);
    }

    private void showMessage(String title, String message) {
        mMessageTitle.setText(title);
        mMessageText.setText(message);
        mMoviesRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mMessageLayout.setVisibility(View.VISIBLE);
    }

    private void showMessage(MovieDbApi.Result apiResult) {
        try {
            JsonParser parser = new JsonParser(apiResult.getResponseBody());
            ApiResponse response = parser.getApiResponse();
            showMessage(
                    getString(R.string.err_api_title),
                    response.getStatusMessage()
            );
        } catch (JSONException e) {
            showMessage(
                    getString(R.string.err_json_format_title),
                    getString(R.string.err_json_format_text));
        }
    }

    /**
     * ApiCallAsyncTask performs the movie database api call in the background and
     * updates the recycler view's adapter if it successfully retrieves data.
     */
    private class ApiCallAsyncTask extends AsyncTask<URL, Void, MovieDbApi.Result> {

        public ApiCallAsyncTask() {
            super();
        }

        /**
         * Performs MovieDbApi.execute() in a background thread
         * @param params URL for the API call. Should be one URL returned by a MovieDbApi method
         * @return MovieDbApi.Result Object that holds the response data for the executed request,
         *         or null if the request failed.
         */
        @Override
        protected MovieDbApi.Result doInBackground(URL... params) {
            MovieDbApi.Result result = null;
            try {
                result = MovieDbApi.execute(params[0]);
            }             catch (IOException e) {
                // empty: swallow the error and allow the function to return null;
            }
            return result;
        }

        /**
         * Show the progress bar so that the user has a visual indication of the background process.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressing();
        }

        /**
         * Show the movie poster grid if the api call was successful, or the appropriate error
         * message on error.
         * @param result The result of tha Api call
         */
        @Override
        protected void onPostExecute(MovieDbApi.Result result) {
            super.onPostExecute(result);
            if (null == result) {
                showMessage(
                        getString(R.string.err_download_data_title),
                        getString(R.string.err_download_data_text));
            } else if (result.isSuccessful()) {
                showPosterGrid(result);
            } else {
                showMessage(result);
            }
        }
    }
}
