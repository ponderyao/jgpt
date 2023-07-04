package io.github.ponderyao.sdk.jgpt.client;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import io.github.ponderyao.sdk.jgpt.constant.OpenGptConstants;
import io.github.ponderyao.sdk.jgpt.exception.GptClientInitializationException;
import io.github.ponderyao.sdk.jgpt.interceptor.AbstractRequestInterceptor;
import io.github.ponderyao.sdk.jgpt.interceptor.AbstractResponseInterceptor;
import io.github.ponderyao.sdk.jgpt.interceptor.DefaultRequestInterceptor;
import io.github.ponderyao.sdk.jgpt.interceptor.DefaultResponseInterceptor;
import okhttp3.OkHttpClient;

/**
 * AbstractOpenGptClient
 *
 * @author PonderYao
 * @since 1.0.0
 */
public abstract class AbstractOpenGptClient {

    public AbstractOpenGptClient(Builder builder) {
        if (StringUtils.isBlank(builder.apiKey)) {
            throw new GptClientInitializationException("open api key is empty");
        }
        if (StringUtils.isBlank(builder.apiHost)) {
            builder.apiHost = OpenGptConstants.OPEN_API_HOST;
        }

        if (Objects.isNull(builder.requestInterceptor)) {
            builder.requestInterceptor = new DefaultRequestInterceptor();
        }
        if (Objects.isNull(builder.responseInterceptor)) {
            builder.responseInterceptor = new DefaultResponseInterceptor();
        }
        builder.requestInterceptor.setApiKey(builder.apiKey);

        if (Objects.isNull(builder.httpClient)) {
            builder.httpClient = defaultOkHttpClient();
        }
        builder.httpClient = builder.httpClient.newBuilder()
            .addInterceptor(builder.requestInterceptor)
            .addInterceptor(builder.responseInterceptor)
            .build();
    }

    protected abstract OkHttpClient defaultOkHttpClient();

    public abstract static class Builder {
        protected String apiKey;
        protected String apiHost = OpenGptConstants.OPEN_API_HOST;
        protected OkHttpClient httpClient;
        protected AbstractRequestInterceptor requestInterceptor;

        protected AbstractResponseInterceptor responseInterceptor;
        
        public abstract Builder apiKey(String apiKey);
        
        public abstract Builder apiHost(String apiHost);
        
        public abstract Builder httpClient(OkHttpClient httpClient);
        
        public abstract Builder requestInterceptor(AbstractRequestInterceptor requestInterceptor);
        
        public abstract Builder responseInterceptor(AbstractResponseInterceptor responseInterceptor);
        
        public abstract AbstractOpenGptClient build();
    }
    
}
