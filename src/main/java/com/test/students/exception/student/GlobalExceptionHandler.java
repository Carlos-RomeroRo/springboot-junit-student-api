package com.test.students.exception.student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<String> handleInvalidAge(InvalidAgeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCurrentSemesterException.class)
    public ResponseEntity<String> handleInvalidSemester(InvalidCurrentSemesterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameEmptyExcpetion.class)
    public ResponseEntity<String> handleNameEmpty(NameEmptyExcpetion ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}