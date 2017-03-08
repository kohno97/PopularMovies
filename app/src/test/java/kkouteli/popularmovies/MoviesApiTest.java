// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies;

import org.junit.Test;

import kkouteli.popularmovies.utilities.MovieDbApi;
import kkouteli.popularmovies.utilities.MovieDbApiException;

import static org.junit.Assert.*;

/**
 * tMDB Api test class
 */
public class MoviesApiTest {
    @Test
    public void testApiKey() {

        String apikey = null;
        try {
            apikey = MovieDbApi.getApiKey();
            System.out.println(">>" + apikey + "<<");
        }
        catch (MovieDbApiException me) {
        }
        assertNotNull(apikey);
    }
}