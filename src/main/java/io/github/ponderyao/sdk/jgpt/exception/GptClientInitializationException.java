package io.github.ponderyao.sdk.jgpt.exception;

/**
 * GptClientInitializationException
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class GptClientInitializationException extends RuntimeException {
    
    private static final long serialVersionUID = 762005639023437360L;

    public GptClientInitializationException(String message, Object... args) {
        super(String.format(message, args));
    }
    
}
