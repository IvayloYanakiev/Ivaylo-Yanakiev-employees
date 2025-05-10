package com.example.sirma.backend.exception;

import com.example.sirma.backend.component.Assignment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ProjectAssignmentAdvice {

    @ExceptionHandler(AssignmentException.class)
    public ResponseEntity<List<Assignment>> handleException(AssignmentException ex) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Collections.emptyList());
    }
}