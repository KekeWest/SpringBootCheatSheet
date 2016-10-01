package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DemoException extends ApplicationException {

    public DemoException(String message) {
        super(message);
    }

    public DemoException() {
    }

}
