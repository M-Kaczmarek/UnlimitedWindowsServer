package pl.unlimited.windows.serwer.error.exception;

public class WindowNotFoundException extends ServiceException {
    public WindowNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
