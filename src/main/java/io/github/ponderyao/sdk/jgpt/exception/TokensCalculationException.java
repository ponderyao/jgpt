package io.github.ponderyao.sdk.jgpt.exception;

/**
 * TokensCalculationException
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class TokensCalculationException extends RuntimeException {
    
    private static final long serialVersionUID = 7538671057996751571L;
    
    public TokensCalculationException(String message, Object... args) {
        super(String.format(message, args));
    }
    
}
