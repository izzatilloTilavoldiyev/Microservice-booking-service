package uz.pdp.bookingservice.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.pdp.bookingservice.dto.response.AppErrorDTO;
import uz.pdp.bookingservice.entity.ErrorMessage;
import uz.pdp.bookingservice.exception.BadRequestException;
import uz.pdp.bookingservice.exception.DataNotFoundException;
import uz.pdp.bookingservice.exception.DuplicateDataException;
import uz.pdp.bookingservice.exception.InvalidEnumValueException;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<AppErrorDTO> dataNotFoundExceptionHandler(RuntimeException e, HttpServletRequest request) {
        AppErrorDTO errorDTO = new AppErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                404
        );
        return ResponseEntity.status(404).body(errorDTO);
    }

    @ExceptionHandler({DuplicateDataException.class})
    public ResponseEntity<ErrorMessage> duplicateDataExceptionHandler(RuntimeException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(409).body(message);
    }

    @ExceptionHandler({InvalidEnumValueException.class})
    public ResponseEntity<ErrorMessage> invalidEnumValueExceptionHandler(RuntimeException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorMessage> badRequestExceptionHandler(RuntimeException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errorMessage = "Input is not valid";
        Map<String, List<String>> errorBody = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errorBody.compute(field, (s, values) -> {
                if (!Objects.isNull(values))
                    values.add(message);
                else
                    values = new ArrayList<>(Collections.singleton(message));
                return values;
            });
        }
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 400);
        return ResponseEntity.status(400).body(errorDTO);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<AppErrorDTO> httpMessageNotReadableExceptionHandler(RuntimeException e, HttpServletRequest request) {
        AppErrorDTO errorDTO = new AppErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                400
        );
        return ResponseEntity.status(400).body(errorDTO);
    }
}
