
package com.example.exception;

import lombok.Getter;

public class ApplicationException extends RuntimeException {

    @Getter
    private final String message;

    public ApplicationException() {
        this(null);
    }

    public ApplicationException(String message) {
        this.message = message;
    }

}
