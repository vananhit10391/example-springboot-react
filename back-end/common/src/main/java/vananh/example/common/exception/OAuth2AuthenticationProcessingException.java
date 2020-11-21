package vananh.example.common.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class OAuth2AuthenticationProcessingException extends AuthenticationException {
    private String message;

    public OAuth2AuthenticationProcessingException(String message) {
        super(message);
        this.message = message;
    }

    public OAuth2AuthenticationProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
