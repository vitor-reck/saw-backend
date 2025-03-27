package br.vitorreck.app.exceptions;

import br.vitorreck.app.exceptions.error.Error;
import br.vitorreck.app.exceptions.error.ErrorValidationResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    ProblemDetail problemDetail = e.getBody();

    List<Error> errors = e.getAllErrors().stream()
        .map(objectError -> new Error(
            (objectError instanceof FieldError fieldError) ? fieldError.getField() : objectError.getObjectName(),
            objectError.getDefaultMessage()
        ))
        .toList();

    ErrorValidationResponse response = new ErrorValidationResponse(
        problemDetail.getTitle(),
        problemDetail.getStatus(),
        problemDetail.getDetail(),
        errors
    );

    return ResponseEntity.status(problemDetail.getStatus()).body(response);
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(DuplicateKeyException.class)
  public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }
}
