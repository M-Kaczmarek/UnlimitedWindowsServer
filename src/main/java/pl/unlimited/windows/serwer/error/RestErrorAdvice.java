package pl.unlimited.windows.serwer.error;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.unlimited.windows.serwer.error.exception.*;

@ControllerAdvice
public class RestErrorAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {OrderDocumentInvalidRequestException.class})
    public ResponseEntity<Error> handleBadRequest(ServiceException e) {
        Error error = new Error(e.getMessage(), e.getErrorCode());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {OrderDocumentNotFoundException.class, WindowSizeNotFoundException.class, WindowNotFoundException.class})
    public ResponseEntity<Error> handleNotFound(ServiceException e) {
        Error error = new Error(e.getMessage(), e.getErrorCode());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
