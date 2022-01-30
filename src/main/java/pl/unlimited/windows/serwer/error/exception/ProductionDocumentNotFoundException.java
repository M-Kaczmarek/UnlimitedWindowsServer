package pl.unlimited.windows.serwer.error.exception;

public class ProductionDocumentNotFoundException extends ServiceException {
    public ProductionDocumentNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
