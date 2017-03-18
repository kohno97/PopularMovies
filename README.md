# PopularMovies

This android application was developed for the Associate Android Developer Fast Track Program (Android Scholarship) by Udacity.

It displays most popular or top rated movies, using the API provided by [The Movie Database](http://www.themoviedb.org).

## Current status

The application is currently on Stage 1. (P1 Submission)

Features implemented:

* The application makes API calls to themoviedb.org and parses the returned JSON to display the results.
* Implements a recycler view with a grid layout in order to display the posters of the movies
* An options menu allowing to select between "Most Popular" and "Top Rated" movies for the poster grid
* A second activity, displaying additional information about a movie. This activity is launched when the user taps or clicks on the poster of a movie of the main activity
* Using android xml resources to define strings and other constants
* Implemented Parcelable in order to pass information between the two activities
* Implemented onSaveInstanceState to (partly) save the current state of the activities.
* Uses SharedPreferences to retain the setting (

## Implementation details &amp; Code organization

### Main package

The package name for the application is `kkouteli.popularmovies`. It contains:

* `MainActivity` which is the launch activity of the application contains the required `RecyclerView`, a `ProgressBar` for providing feedback to the user while downloading content, and a `LinearLayout` with two `TextView`s in order to display error messages. Choice between Most Popular and Top Rated movies is made through two menu items inserted at the options menu; both are checkable and display a radio button to indicate the active one.
* `MovieDetailsActivity`, which displays detailed movie information. LinearLayout is used to place individual elements. A `ScrollView` is used to enable the user to view all contents.
* `MoviesAdapter` implements the recycler view adapter for displaying the poster grid. The `MovieViewHolder` declared as a nested class contains a single ImageView item for displaying the movie's poster image, and a reference to the bound movie. The "on click" handler the holder assigns to the `ImageView` "puts" the referenced `Movie` as an extra on the intent used to start the movie details activity in order to pass that information.

### Models sub-package

Subpackage kkouteli.popularmovies.models contains three classes:

* `Movie` represents information for a single movie. Fields contained are limited only to the data required by the application and is commonly returned by both calls to the movie database for the "Most popular" and "Top rated" requests; currently additional information retrieved from the API call for Movie details is not included in this class.
* `MovieList` contains information returned by the "Most popular" and "Top rated" requests. The movies returned in the "results" field are retrieved with the `getMovies()` method.
* `ApiResponse` reflects fields return by erroneous API calls. This is used to retrieve an error message (if any) from the database and display it to the user.

### Utilities sub-package

Subpackage kkouteli.popularmovies.utilities contains the following classes:

* `JsonParser` which provides methods to parse the returned data into the above model classes.
* `MovieDbApi` provides static methods and fields for the API implementation. Use the functions to retrieve the proper API URL and the `execute()` method to retrieve the results. An object of the `Result` nested class is returned from the `execute()` method that holds response code and body.
* `MovieDbApiException` is defined to for API-related errors.

## API Key setup

Proper function of the application requires the use of an API Key provided by The Movie Database. Save the key in a plain text file as `app/src/main/resources/apikey.txt`. 

*The only contents of the file should be your API Key string, optionally with a new line character following immediately after its last character.*

Note that this is a Java-resource file, not an Android resource file.

If you do not provide a key, the application may still compile and execute properly on your device/emulator; however, without a proper key it will simply display an error message on screen.

