package io.github.ponderyao.sdk.jgpt.interceptor;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * AbstractRequestInterceptor: abstract okhttp request interceptor
 *
 * @author PonderYao
 * @since 1.0.0
 */
public abstract class AbstractRequestInterceptor implements Interceptor {
    
    private String apiKey;
    
    protected abstract void beforeProceedRequest(Request request);

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = authorise(chain.request());
        beforeProceedRequest(request);
        return chain.proceed(request);
    }

    protected Request authorise(Request request) {
        return request.newBuilder()
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .method(request.method(), request.body())
            .build();
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
