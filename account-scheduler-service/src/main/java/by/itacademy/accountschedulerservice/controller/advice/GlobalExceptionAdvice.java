package by.itacademy.accountschedulerservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseError> securityHandler(IllegalArgumentException e) {
        return new ResponseEntity<>(new ResponseError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> securityHandler(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new ResponseError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseError> securityHandler(NoSuchElementException e) {
        return new ResponseEntity<>(new ResponseError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> securityHandler(RuntimeException e){
        return new ResponseEntity<>(new ResponseError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<MultiplyResponseError> securityHandler(ValidationException e) {
        return new ResponseEntity<>(new MultiplyResponseError(e.getErrors()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
