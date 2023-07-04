package io.github.ponderyao.sdk.jgpt.exception;

/**
 * OpenApiCallException
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class OpenApiCallException extends RuntimeException {
    
    private static final long serialVersionUID = -1445182137892297897L;
    
    public OpenApiCallException(String message, Object... args) {
        super(String.format(message, args));
    }
    
    public OpenApiCallException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
