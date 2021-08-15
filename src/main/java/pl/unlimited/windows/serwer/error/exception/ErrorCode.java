package pl.unlimited.windows.serwer.error.exception;

public enum ErrorCode {

    ORDER_DOCUMENT_NOT_FOUND(1000);



    final int statusCode;

    ErrorCode(int statusCode) {
        this.statusCode=statusCode;
    }

    String getByStatusCode(int statusCode) {
        for (ErrorCode value : ErrorCode.values()) {
            if (value.statusCode == statusCode) {
                return value.name();
            }
        }
        throw new ErrorCodeNotFoundException("Error code not found");
    }

}
