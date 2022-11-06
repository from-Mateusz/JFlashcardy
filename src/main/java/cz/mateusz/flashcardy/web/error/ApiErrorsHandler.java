package cz.mateusz.flashcardy.web.error;

import cz.mateusz.flashcardy.exception.FailedAuthenticationException;
import cz.mateusz.flashcardy.web.data.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiErrorsHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FailedAuthenticationException.class)
    ApiError handleFailedAuthenticationException(HttpServletRequest req, Exception exception) {
        ApiError error = new ApiError(exception.getMessage());
        return error;
    }
}
