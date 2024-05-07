package bigs.task.global.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("bigs.task")
public class ErrorHandlerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception e) {

        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
