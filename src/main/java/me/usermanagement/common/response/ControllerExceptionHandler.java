package me.usermanagement.common.response;

import me.usermanagement.common.response.errorClasses.CustomException;
import me.usermanagement.common.response.messages.error.ErrorCodeDetailEnum;
import me.usermanagement.common.response.messages.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

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
    protected ResponseEntity<Response> handleCustomException(CustomException customException) {
        ErrorCodeDetailEnum errorCodeDetailEnum = customException.getErrorCodeDetailEnum();
        Response response = new Response.
                ResponseBuilder()
                .statusCode(errorCodeDetailEnum.getStatusCode())
                .message(errorCodeDetailEnum.getErrorMessage())
                .responseText(errorCodeDetailEnum.getResponseText())
                .builder();
        return new ResponseEntity<>(response, HttpStatus.resolve(errorCodeDetailEnum.getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> methodValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        FieldError fe = bindingResult.getFieldError();
        String message;
        if (fe != null) {
            message = "Request Error" + " " + fe.getField() + "=" + fe.getRejectedValue() + " (" + fe.getDefaultMessage() + ")";
        } else {
            message = ErrorCodeDetailEnum.getResponseText(ErrorMessage.INVALID_PARAMETER);
        }
        Response response = new Response.
                ResponseBuilder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .responseText(message)
                .builder();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
