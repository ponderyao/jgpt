package io.github.ponderyao.sdk.jgpt.interceptor;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import io.github.ponderyao.sdk.jgpt.domain.common.OpenResponse;
import io.github.ponderyao.sdk.jgpt.util.JsonUtils;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * AbstractResponseInterceptor: abstract okhttp response interceptor
 *
 * @author PonderYao
 * @since 1.0.0
 */
public abstract class AbstractResponseInterceptor implements Interceptor {
    
    protected static final String AUTHENTICATION_ERROR_RESPONSE = "401";
    protected static final String ACCOUNT_LIMIT_ERROR_RESPONSE = "429";
    protected static final String SYSTEM_INTERNAL_ERROR_RESPONSE = "500";
    
    protected abstract void processKnownErrorResponse(OpenResponse<?> errorResponse, int errorCode);
    
    protected abstract void processUnKnownErrorResponse(OpenResponse<?> errorResponse);

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.isSuccessful()) {
            OpenResponse<?> errorResponse = toErrorResponse(response);
            if (isKnownError(response)) {
                processKnownErrorResponse(errorResponse, response.code());
            } else {
                processUnKnownErrorResponse(errorResponse);
            }
        }
        return response;
    }
    
    protected boolean isKnownError(Response response) {
        return StringUtils.equalsAny(String.valueOf(response.code()), 
            AUTHENTICATION_ERROR_RESPONSE, ACCOUNT_LIMIT_ERROR_RESPONSE, SYSTEM_INTERNAL_ERROR_RESPONSE);
    }
    
    protected OpenResponse<?> toErrorResponse(Response response) throws IOException {
        if (Objects.isNull(response.body())) {
            return null;
        }
        String errorContent = response.body().string();
        return JsonUtils.fromJson(errorContent, OpenResponse.class);
    }
    
}
