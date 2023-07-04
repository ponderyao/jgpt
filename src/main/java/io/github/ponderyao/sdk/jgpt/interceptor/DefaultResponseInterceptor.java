package io.github.ponderyao.sdk.jgpt.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.ponderyao.sdk.jgpt.domain.common.OpenResponse;
import io.github.ponderyao.sdk.jgpt.exception.OpenApiCallException;

/**
 * DefaultResponseInterceptor: default okhttp response interceptor
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class DefaultResponseInterceptor extends AbstractResponseInterceptor {
    
    public static final Logger log = LoggerFactory.getLogger(DefaultResponseInterceptor.class);

    @Override
    protected void processKnownErrorResponse(OpenResponse<?> errorResponse, int errorCode) {
        log.error("open api request failed, the error code: {}", errorCode);
        throw new OpenApiCallException("open api request failed: ", errorResponse.getError().getMessage());
    }

    @Override
    protected void processUnKnownErrorResponse(OpenResponse<?> errorResponse) {
        log.error("open api request failed: {}", errorResponse.getError().getMessage());
        throw new OpenApiCallException("open api request failed: ", errorResponse.getError().getMessage());
    }
    
}
