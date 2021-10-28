package com.MQueiroz.integrations.resources.exceptions;

import com.MQueiroz.integrations.services.exceptions.EmailException;
import com.MQueiroz.integrations.services.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<StandardError> email(EmailException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), "Email Error", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
