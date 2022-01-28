package me.egg_nuts.user_manager.controller;

import me.egg_nuts.user_manager.error.CustomException;
import me.egg_nuts.user_manager.message.ErrorCodeDetailEnum;
import me.egg_nuts.user_manager.message.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler{



    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Response> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        Response response = new Response.
                 ResponseBuilder()
                .statusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
                .responseText(e.getMessage())
                .builder();


        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Response> handleCustomException(CustomException customException){
        ErrorCodeDetailEnum errorCodeDetailEnum = customException.getErrorCodeDetailEnum();

        Response response = new Response.
                                          ResponseBuilder()
                                          .statusCode(errorCodeDetailEnum.getStatusCode())
                                           .message(errorCodeDetailEnum.getErrorMessage())
                                          .responseText(errorCodeDetailEnum.getResponseText())
                                         .builder();

        return new ResponseEntity<>(response, HttpStatus.resolve(errorCodeDetailEnum.getStatusCode()));
    }
}
