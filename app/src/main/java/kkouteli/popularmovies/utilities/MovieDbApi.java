// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies.utilities;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;

/**
 * Contains the implementation of the API calls to The Movie DataBase
 */
public class MovieDbApi {

    /**
     * Base URI for tMDB API requests
     */
    public static final String BASE_URI = "https://api.themoviedb.org/3";

    /**
     * URI for "most popular" movies request
     */
    public static final String REQUEST_URI_POPULAR = BASE_URI + "/movie/popular";

    /**
     * URI for "top rated" movies request
     */
    public static final String REQUEST_URI_TOP_RATED = BASE_URI + "/movie/top_rated";

    /**
     * URI for movie details request
     */
    public static final String REQUEST_URI_MOVIE = BASE_URI + "/movie/";

    /**
     * API parameter: API key
     */
    public static final String PARAM_API_KEY = "api_key";

    /**
     * API parameter: page
     */
    public static final String PARAM_PAGE = "page";

    /**
     * Base URI for tMDB image requests
     */
    public static final String BASE_IMAGE_URI = "http://image.tmdb.org/t/p/";

    /**
     * Path for 185px wide images
     */
    public static final String IMAGE_SIZE_PATH_185 = "w185";

    /**
     * Path for 342px wide images
     */
    public static final String IMAGE_SIZE_PATH_342 = "w342";

    /**
     * Path for 500px wide images
     */
    public static final String IMAGE_SIZE_PATH_500 = "w500";

    /**
     * Default image size path; for phones we will stick to 185
     */
    public static final String IMAGE_SIZE_PATH_DEFAULT = IMAGE_SIZE_PATH_185;

    /**
     * Java-resource file to read the API key from
     */
    private static final String API_KEY_RESOURCE_FILE = "apikey.txt";

    /**
     * API key cached value.
     * @see public static String getApiKey()
     */
    private static String sApiKey = null;

    /**
     * Retrieves the tMDB API key from Java resource file apikey.txt
     * The API key value is stored in sApiKey so that subsequent calls to this function
     * will not read perform file access.
     * @return String tMDB API v3 API Key
     * @throws MovieDbApiException If the specified resource file does not exist
     *         or other IO Exception occurs
     */
    public static String getApiKey() throws MovieDbApiException {
        if (sApiKey == null) {
            URL resUrl = MovieDbApi.class.getClassLoader().getResource(API_KEY_RESOURCE_FILE);
            if (resUrl == null) {
                throw new MovieDbApiException();
            }
            File file = new File(resUrl.getFile());
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                try {
                    sApiKey = br.readLine();
                } finally {
                    br.close();
                }
            } catch (FileNotFoundException fnfe) {
                throw new MovieDbApiException(fnfe.getMessage());
            } catch (IOException ioe) {
                throw new MovieDbApiException(ioe.getMessage());
            }
        }
        return sApiKey;
    }

    /**
     * Builds a URL object for the most popular movies tMDB API request, only for the first
     * page of results.
     * @return The URL for the most popular movies API request
     */
    public static URL getPopularUrl() throws MovieDbApiException {
        return getPopularUrl(1);
    }

    /**
     * Builds a URL object for the most popular movies tMDB API request, requesting a specific
     * results page.
     * @param page The page index, 1-based
     * @return The URL for the specified page of the most popular movies API request
     */
    public static URL getPopularUrl(int page) throws MovieDbApiException {
        Uri uri = Uri.parse(REQUEST_URI_POPULAR).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, getApiKey())
                .appendQueryParameter(PARAM_PAGE, String.valueOf(page))
                .build();
        return getUrlFromUri(uri);
    }

    /**
     * Builds a URL object for the top rated movies tMDB API request, only for the first
     * page of results.
     * @return The URL for the most popular movies API request
     */
    public static URL getTopRatedUrl() throws MovieDbApiException {
        return getTopRatedUrl(1);
    }

    /**
     * Builds a URL object for the top rated movies tMDB API request, requesting a specific
     * results page.
     * @param page The page index, 1-based
     * @return The URL for the specified page of the most popular movies API request
     */
    public static URL getTopRatedUrl(int page) throws MovieDbApiException {
        Uri uri = Uri.parse(REQUEST_URI_TOP_RATED).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, getApiKey())
                .appendQueryParameter(PARAM_PAGE, String.valueOf(page))
                .build();
        return getUrlFromUri(uri);
    }

    /**
     * Builds a URL object for the movie details tMDB API request
     * @param movieId long Movie id as provided by the API
     * @return The URL for the specified movie detail page API request
     */
    public static URL getMovieDetailsUrl(long movieId) throws MovieDbApiException {
        Uri uri = Uri.parse(REQUEST_URI_MOVIE).buildUpon()
                .appendPath(String.valueOf(movieId))
                .appendQueryParameter(PARAM_API_KEY, getApiKey())
                .build();
        return getUrlFromUri(uri);
    }

    /**
     * Builds a URL object for the image path returned from an API request,
     * using the default image size path
     * @param imagePath String image path as returned from an API response
     * @return The URL for the image
     */
    public static URL getMovieImageUrl(String imagePath) throws MovieDbApiException {
        return getMovieImageUrl(imagePath, IMAGE_SIZE_PATH_DEFAULT);
    }

    /**
     * Builds a URL object for the image path returned from an API request,
     * using the specified image size path
     * @param imagePath String Image path as returned from an API response
     * @param sizePath String Image size path of the URL
     * @return The URL for the image
     * @throws MovieDbApiException if sizePath does not evaluate to a path used by the API.
     */
    public static URL getMovieImageUrl(String imagePath, String sizePath) throws MovieDbApiException {
        if (!(
                sizePath.equals(IMAGE_SIZE_PATH_185) ||
                sizePath.equals(IMAGE_SIZE_PATH_342) ||
                sizePath.equals(IMAGE_SIZE_PATH_500))) {
            throw new MovieDbApiException();
        }
        Uri uri = Uri.parse(BASE_IMAGE_URI).buildUpon()
                .appendPath(sizePath)
                .appendPath(imagePath)
                .build();
        return getUrlFromUri(uri);
    }


    private static URL getUrlFromUri(Uri uri) throws MovieDbApiException {
        String urlString = uri.toString();
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new MovieDbApiException(e.getMessage());
        }
    }

}
