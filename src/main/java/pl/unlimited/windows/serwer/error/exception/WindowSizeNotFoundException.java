package pl.unlimited.windows.serwer.error.exception;

public class WindowSizeNotFoundException extends ServiceException {
    public WindowSizeNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}