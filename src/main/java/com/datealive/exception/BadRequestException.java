package com.datealive.exception;

/**
 * @ClassName: BadRequestException
 * @Description: TODO
 * @author: datealive
 * @date: 2021/4/26  10:44
 */
public class BadRequestException extends RuntimeException{

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
