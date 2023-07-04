package io.github.ponderyao.sdk.jgpt.client;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatCompletion;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatMessage;
import io.github.ponderyao.sdk.jgpt.exception.OpenApiCallException;
import io.github.ponderyao.sdk.jgpt.interceptor.AbstractRequestInterceptor;
import io.github.ponderyao.sdk.jgpt.interceptor.AbstractResponseInterceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;

/**
 * OpenGptStreamClient: OpenAI-ChatGPT client for stream response
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class OpenGptStreamClient extends AbstractOpenGptClient {
    
    private String apiKey;
    
    private String apiHost;
    
    private OkHttpClient httpClient;

    public OpenGptStreamClient(Builder builder) {
        super(builder);
        
        this.setApiKey(builder.apiKey);
        this.setApiHost(builder.apiHost);
        this.setHttpClient(builder.httpClient);
    }

    @Override
    protected OkHttpClient defaultOkHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();
    }

    public void streamChatCompletion(ChatCompletion chatCompletion, EventSourceListener eventSourceListener) {
        if (Objects.isNull(eventSourceListener)) {
            throw new OpenApiCallException("the event-source-listener for stream calling open api is empty");
        }
        chatCompletion.setStream(true);
        try {
            EventSource.Factory factory = EventSources.createFactory(this.httpClient);
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(chatCompletion);
            Request request = new Request.Builder()
                .url(this.apiHost + "v1/chat/completions")
                .post(RequestBody.Companion.create(requestBody, MediaType.Companion.parse("application/json")))
                .header("Authorization", "Bearer " + apiKey)
                .build();
            factory.newEventSource(request, eventSourceListener);
        } catch (Exception e) {
            throw new OpenApiCallException("open api request failed by stream pattern", e);
        }
    }
    
    public void streamChatCompletion(List<ChatMessage> messages, EventSourceListener eventSourceListener) {
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(messages).stream(true).build();
        this.streamChatCompletion(chatCompletion, eventSourceListener);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractOpenGptClient.Builder {

        @Override
        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        @Override
        public Builder apiHost(String apiHost) {
            this.apiHost = apiHost;
            return this;
        }

        @Override
        public Builder httpClient(OkHttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        @Override
        public Builder requestInterceptor(AbstractRequestInterceptor requestInterceptor) {
            this.requestInterceptor = requestInterceptor;
            return this;
        }

        @Override
        public Builder responseInterceptor(AbstractResponseInterceptor responseInterceptor) {
            this.responseInterceptor = responseInterceptor;
            return this;
        }
        
        @Override
        public OpenGptStreamClient build() {
            return new OpenGptStreamClient(this);
        }
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
