package pl.unlimited.windows.serwer.error.exception;

public class OrderDocumentNotFoundException extends ServiceException {
    public OrderDocumentNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
