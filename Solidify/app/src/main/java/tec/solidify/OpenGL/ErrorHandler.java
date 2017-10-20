package tec.solidify.OpenGL;

/**
 * Created by josea on 9/27/2017.
 */

interface ErrorHandler {
    enum ErrorType {
        BUFFER_CREATION_ERROR
    }

    void handleError(ErrorType errorType, String cause);
}