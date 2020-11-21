package vananh.example.common.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
