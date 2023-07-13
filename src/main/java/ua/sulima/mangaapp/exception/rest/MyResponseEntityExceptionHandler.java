package ua.sulima.mangaapp.exception.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MyResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(CustomNotFoundException ex, WebRequest webRequest){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .details(webRequest.getDescription(false))
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
