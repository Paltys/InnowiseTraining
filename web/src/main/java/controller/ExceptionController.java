package controller;

import exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import service.response.ErrorResponse;

import java.io.IOException;

@ControllerAdvice
class ExceptionController extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<?> responseException(Exception e) {
//        ErrorResponse errorResponse = new ErrorResponse(500,"Sorry man");
//        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<?> responseException(IOException e) {
        ErrorResponse errorResponse = new ErrorResponse(400, "File is bad");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> responseException(EntityNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(404,e.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
