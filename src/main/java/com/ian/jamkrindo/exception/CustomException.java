package com.ian.jamkrindo.exception;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class CustomException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus httpStatus;

    public CustomException(String errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
