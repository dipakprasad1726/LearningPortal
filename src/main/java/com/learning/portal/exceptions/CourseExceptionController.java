package com.learning.portal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CourseExceptionController {
    @ExceptionHandler(value = CourseNotFoundException.class)
    public ResponseEntity<Object> exception(CourseNotFoundException exception) {
        return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CountryNotFoundException.class)
    public ResponseEntity<Object> exception(CountryNotFoundException countryNotFoundException){
        return new ResponseEntity<>("Country provided does not exist in our database",HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = QueryFailureException.class)
    public ResponseEntity<Object> exception(QueryFailureException countryNotFoundException){
        return new ResponseEntity<>("Database query failed due to server issue.",HttpStatus.BAD_REQUEST);
    }
}
