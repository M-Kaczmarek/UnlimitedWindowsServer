package pl.unlimited.windows.serwer.error.exception;

public class PackingDocumentNotFoundException extends ServiceException {
    public PackingDocumentNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

