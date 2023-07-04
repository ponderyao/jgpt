package io.github.ponderyao.sdk.jgpt.client;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

import io.github.ponderyao.sdk.jgpt.api.OpenAccountApi;
import io.github.ponderyao.sdk.jgpt.api.OpenGptApi;
import io.github.ponderyao.sdk.jgpt.domain.account.AccountBillingUsage;
import io.github.ponderyao.sdk.jgpt.domain.account.AccountSubscription;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatCompletion;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatCompletionResponse;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatMessage;
import io.github.ponderyao.sdk.jgpt.interceptor.AbstractRequestInterceptor;
import io.github.ponderyao.sdk.jgpt.interceptor.AbstractResponseInterceptor;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * OpenGptDirectClient: OpenAI-ChatGPT client for direct response
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class OpenGptDirectClient extends AbstractOpenGptClient {

    private final OpenGptApi openGptApi;
    
    private final OpenAccountApi openAccountApi;
    
    public OpenGptDirectClient(Builder builder) {
        super(builder);
        
        this.openGptApi = new Retrofit.Builder()
            .baseUrl(builder.apiHost)
            .client(builder.httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(OpenGptApi.class);
        
        this.openAccountApi = new Retrofit.Builder()
            .baseUrl(builder.apiHost)
            .client(builder.httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(OpenAccountApi.class);
    }
    
    @Override
    protected OkHttpClient defaultOkHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
    }
    
    public ChatCompletionResponse chatCompletion(ChatCompletion chatCompletion) {
        Single<ChatCompletionResponse> chatCompletionResponse = this.getOpenGptApi().chatCompletion(chatCompletion);
        return chatCompletionResponse.blockingGet();
    }

    public ChatCompletionResponse chatCompletion(List<ChatMessage> messages) {
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(messages).build();
        return this.chatCompletion(chatCompletion);
    }
    
    public AccountSubscription querySubscription() {
        return this.openAccountApi.subscription().blockingGet();
    }
    
    public AccountBillingUsage queryUsage(@NotNull LocalDate startDate, @NotNull LocalDate endDate) {
        return this.openAccountApi.usage(startDate, endDate).blockingGet();
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
        public OpenGptDirectClient build() {
            return new OpenGptDirectClient(this);
        }
    }

    public OpenAccountApi getOpenAccountApi() {
        return openAccountApi;
    }

    public OpenGptApi getOpenGptApi() {
        return this.openGptApi;
    }
}
