package br.com.elo7.sonda.candidato.api.error;

import br.com.elo7.sonda.candidato.domain.exception.CommandException;
import br.com.elo7.sonda.candidato.domain.exception.MovementException;
import br.com.elo7.sonda.candidato.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CommandException.class)
    public ResponseEntity<StandardError> handleCommand(CommandException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Command to probe error";
        return new ResponseEntity<>(new StandardError(status.value(), errors, message), status);
    }

    @ExceptionHandler(MovementException.class)
    public ResponseEntity<StandardError> handleMovement(MovementException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Probe movement error";
        return new ResponseEntity<>(new StandardError(status.value(), errors, message), status);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handleNotFound(NotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Entity not found";
        return new ResponseEntity<>(new StandardError(status.value(), errors, message), status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleGeneric(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Generic error";
        return new ResponseEntity<>(new StandardError(status.value(), errors, message), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> String.join(" ", fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Validation field error";
        return new ResponseEntity<>(new StandardError(status.value(), errors, message), status);
    }


}
