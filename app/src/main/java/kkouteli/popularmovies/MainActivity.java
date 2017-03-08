// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.URL;

import kkouteli.popularmovies.utilities.MovieDbApi;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mMoviesRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviesRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_waiting);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mMoviesRecyclerView.setLayoutManager(gridLayoutManager);

        mMoviesRecyclerView.setHasFixedSize(true);
    }

    private void showProgressing() {
        mMoviesRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void showPosterGrid() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mMoviesRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showMessage(String title, String message) {

    }

    private class ApiCallAsyncTask extends AsyncTask<URL, Void, MovieDbApi.Result> {

        public ApiCallAsyncTask() {
            super();
        }

        @Override
        protected MovieDbApi.Result doInBackground(URL... params) {
            MovieDbApi.Result result = null;
            try {
                result = MovieDbApi.execute(params[0]);
            }
            catch (IOException e) {
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressing();
        }

        @Override
        protected void onPostExecute(MovieDbApi.Result result) {
            super.onPostExecute(result);
            if (null == result) {

            } else {
                showPosterGrid();
            }
        }
    }
}
