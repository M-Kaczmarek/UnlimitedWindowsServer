package pl.unlimited.windows.serwer.error.exception;

public class ServiceException extends RuntimeException {

    private ErrorCode errorCode;

    public ServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
