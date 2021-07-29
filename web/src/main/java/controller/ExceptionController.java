package controller;

import exceptions.EntityNotFoundException;
import exceptions.ViolationErrorCustom;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import service.response.ErrorResponse;

import java.io.IOException;

@ControllerAdvice()
class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse responseException(Exception e) {
        return new ErrorResponse(500, e.getMessage());
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse responseException(IOException e) {
        return new ErrorResponse(500, e.getMessage());

    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse responseException(EntityNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    @ExceptionHandler(value=ViolationErrorCustom.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onConstraintValidationException(
            ViolationErrorCustom e) {
        return new ErrorResponse(400, e.getMessage());
    }
}
