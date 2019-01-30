package com.game.bowling.error;

import com.game.bowling.error.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Provides exception handling functionality.
 */
@ControllerAdvice
@ResponseBody
@Slf4j
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BowlingGameExceptionHandler {


    /**
     * Bad request
     */
    @ExceptionHandler(value = InvalidParameterException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorInfoResponse handleInvalidParameterException(HttpServletRequest request, InvalidParameterException invalidParameterException) {
        return ErrorInfoResponse.builder()
                .errorCode("400")
                .url(request.getRequestURL().toString())
                .exception("InvalidParameterException")
                .message(invalidParameterException.getMessage())
                .build();
    }
}
