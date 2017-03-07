// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies.utilities;

/**
 * The Movie Database API exception
 * Some methods of MovieDbApi may throw an exception of MovieDbApiException
 * to signal some API failure.
 */
public final class MovieDbApiException extends Exception {
    public MovieDbApiException() {
        super();
    }
    public MovieDbApiException(String msg) {
        super(msg);
    }
}
