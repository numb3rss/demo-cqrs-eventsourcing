package com.demo.cqrseventsourcing.webapiwrite;

import com.demo.cqrseventsourcing.webapiwrite.adapters.secondary.InfrastructureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {InfrastructureException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest request) {
        String responseBody = exception.getMessage();
        return ResponseEntity.status(500).body(responseBody);
    }
}
