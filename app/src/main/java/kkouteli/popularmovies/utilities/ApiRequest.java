// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ApiRequest is a simple wrapper class around URLConnection.
 * Create an ApiRequest object for a URL, then call execute() to populate
 * response body and code members.
 */
public class ApiRequest {

    /**
     * The URL for the API request
     */
    private URL     mRequestUrl;

    /**
     * Response code
     */
    private int     mResponseCode = 0;

    /**
     * Response body
     */
    private String  mResponseBody = null;

    /**
     * Indicates if the request has been executed
     */
    private boolean mExecuted;

    /**
     * HTTP response code indicating success
     */
    public static final int CODE_SUCCESS = HttpURLConnection.HTTP_OK;

    /**
     * Size of the buffer to use for reading API request's response.
     */
    private static final int BUFFER_SIZE = 4096;  // 4kBytes

    /**
     * Create an API request object for a specific URL.
     * @param url API URL
     * @throws IllegalArgumentException if url is null
     */
    public ApiRequest(URL url) {
        if (url == null) {
            throw new IllegalArgumentException();
        }
        this.mRequestUrl = url;
    }

    /**
     * Execute the request for the URL provided at the construction of the object and
     * store the response code and body.
     * @return boolean If the response indicates success, by means of HTTP response code
     * @throws IOException Related to network or data read errors
     */
    public boolean execute() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) mRequestUrl.openConnection();
        mResponseCode = connection.getResponseCode();
        try {
            StringBuilder builder = new StringBuilder();
            InputStream stream = connection.getErrorStream();
            if (stream == null) {
                stream = connection.getInputStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            try {
                int count;
                char[] buffer = new char[BUFFER_SIZE];
                while ((count = br.read(buffer, 0, BUFFER_SIZE)) >= 0) {
                    builder.append(buffer, 0, count);
                }
            }
            finally {
                br.close();
            }
            mResponseBody = builder.toString();
        }
        finally {
            mExecuted = true;
            connection.disconnect();
        }

        return CODE_SUCCESS == mResponseCode;
    }

    /**
     * @return String The body of the response for the request
     * @throws IllegalStateException on a call to getResponseBody without prior invocation of execute()
     */
    public String getResponseBody() {
        if (mExecuted) {
            return mResponseBody;
        }
        else {
            throw new IllegalStateException();
        }
    }

    /**
     * @return int The HTTP response code for the request
     * @throws IllegalStateException on a call to getResponseBody without prior invocation of execute()
     */
    public int getResponseCode() {
        if (mExecuted) {
            return mResponseCode;
        }
        else {
            throw new IllegalStateException();
        }
    }
}
