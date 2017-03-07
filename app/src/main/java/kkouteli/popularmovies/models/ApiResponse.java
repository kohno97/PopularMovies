// Popular Movies Android application
// Udacity Android Developer Fast Track Program
// Koutelidakis Konstantinos (kkouteli@gmail.com)

package kkouteli.popularmovies.models;

/**
 * Holds response data
 */
public class ApiResponse {

    private String  mStatusMessage;
    private int     mStatusCode;
    private boolean mSuccess;

    public ApiResponse() {
        this(null, 0, false);
    }
    public ApiResponse(String message, int code) {
        this(message, code, false);
    }
    public ApiResponse(String message, int code, boolean success) {
        mStatusMessage = message;
        mStatusCode = code;
        mSuccess = success;
    }

    public String getStatusMessage() {
        return mStatusMessage;
    }
    public ApiResponse setStatusMessage(String message) {
        mStatusMessage = message;
        return this;
    }

    public int getStatusCode() {
        return mStatusCode;
    }
    public ApiResponse setStatusCode(int code) {
        mStatusCode = code;
        return this;
    }

    public boolean getSuccess() {
        return mSuccess;
    }
    public ApiResponse setSuccess(boolean success) {
        mSuccess = success;
        return this;
    }
}
