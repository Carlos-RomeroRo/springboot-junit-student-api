package com.test.students.exception.student;

public class InvalidCurrentSemesterException extends RuntimeException {
    public InvalidCurrentSemesterException(String message) {
        super(message);
    }
}
