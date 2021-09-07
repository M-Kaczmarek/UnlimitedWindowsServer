package pl.unlimited.windows.serwer.error.exception;

public class OrderDocumentInvalidRequestException extends ServiceException {
    public OrderDocumentInvalidRequestException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
