package io.github.raphaelrighetti.friendsapi.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity sqlIntegrityConstraintViolation(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.badRequest().body(new ExceptionMessageDTO(e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrorDTO>> methodArgumentNotValid(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<FieldErrorDTO> dtoList = fieldErrors.stream().map(FieldErrorDTO::new).toList();

        return ResponseEntity.badRequest().body(dtoList);
    }

    private record ExceptionMessageDTO(String message) {
    }

    private record FieldErrorDTO(String field, String message) {
        public FieldErrorDTO(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
