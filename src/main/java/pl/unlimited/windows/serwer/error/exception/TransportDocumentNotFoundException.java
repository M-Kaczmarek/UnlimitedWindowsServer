package pl.unlimited.windows.serwer.error.exception;

public class TransportDocumentNotFoundException extends ServiceException {
    public TransportDocumentNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
