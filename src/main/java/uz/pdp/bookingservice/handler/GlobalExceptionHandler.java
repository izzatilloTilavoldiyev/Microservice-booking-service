package uz.pdp.bookingservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.pdp.bookingservice.entity.ErrorMessage;
import uz.pdp.bookingservice.exception.BadRequestException;
import uz.pdp.bookingservice.exception.DataNotFoundException;
import uz.pdp.bookingservice.exception.DuplicateDataException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<ErrorMessage> dataNotFoundExceptionHandler(RuntimeException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(404).body(message);
    }

    @ExceptionHandler({DuplicateDataException.class})
    public ResponseEntity<ErrorMessage> duplicateDataExceptionHandler(RuntimeException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(409).body(message);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorMessage> IllegalArgumentExceptionHandler(RuntimeException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorMessage> badRequestExceptionHandler(RuntimeException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(400).body(message);
    }
}
