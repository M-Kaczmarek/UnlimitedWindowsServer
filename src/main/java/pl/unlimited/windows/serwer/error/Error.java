package pl.unlimited.windows.serwer.error;

import pl.unlimited.windows.serwer.error.exception.ErrorCode;

public class Error {

    private final String message;

    private final ErrorCode errorCode;

    public Error(String message, ErrorCode errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}