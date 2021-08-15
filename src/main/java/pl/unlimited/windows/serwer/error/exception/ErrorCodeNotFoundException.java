package pl.unlimited.windows.serwer.error.exception;
public class ErrorCodeNotFoundException extends ServiceException {

    public ErrorCodeNotFoundException(String message) {
        super(message, null);
    }
}

