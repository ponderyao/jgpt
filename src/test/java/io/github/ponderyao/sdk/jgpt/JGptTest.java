package io.github.ponderyao.sdk.jgpt;

import java.time.LocalDate;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.github.ponderyao.sdk.jgpt.client.OpenGptDirectClient;
import io.github.ponderyao.sdk.jgpt.client.OpenGptStreamClient;
import io.github.ponderyao.sdk.jgpt.constant.ChatGptModel;
import io.github.ponderyao.sdk.jgpt.constant.ChatRole;
import io.github.ponderyao.sdk.jgpt.domain.account.AccountBillingUsage;
import io.github.ponderyao.sdk.jgpt.domain.account.AccountSubscription;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatCompletion;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatCompletionResponse;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatMessage;
import io.github.ponderyao.sdk.jgpt.util.JsonUtils;
import okhttp3.OkHttpClient;

/**
 * JGptTest: unit test
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class JGptTest {

    /**
     * <a href="https://platform.openai.com/account/api-keys">...</a>
     */
    private static final String API_KEY = "sk-****************";

    /**
     * 中国国内服务需使用代理地址，建议使用国外的云函数服务来代理open api，已验证阿里云云函数新加坡地区可行
     */
    private static final String API_HOST = "https://***.ap-southeast-1.fcapp.run/";
    
    private OpenGptDirectClient getClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
        return OpenGptDirectClient.builder()
            .apiKey(API_KEY)
            // Chinese developer should use the proxy config
            .apiHost(API_HOST)
            .httpClient(httpClient)
            .build();
    }
    
    @Test
    public void chatCompletion() {
        OpenGptDirectClient client = getClient();
        ChatMessage message = ChatMessage.builder()
            .role(ChatRole.USER.getRoleName())
            .content("Introduce the city of Guangzhou in China")
            .build();
        ChatCompletion chatCompletion = ChatCompletion.builder()
            .model(ChatGptModel.GPT_3_5_TURBO.getModelName())
            .messages(Collections.singletonList(message))
            .build();
        ChatCompletionResponse response = client.chatCompletion(chatCompletion);
        System.out.println(JsonUtils.toJson(response.getChoices().get(0).getMessage()));
    }
    
    @Test
    public void streamChatCompletion() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
        OpenGptStreamClient client = OpenGptStreamClient.builder()
            .apiKey(API_KEY)
            // Chinese developer should use the proxy config
            .apiHost(API_HOST)
            .httpClient(httpClient)
            .build();

        TestEventSourceListener eventSourceListener = new TestEventSourceListener();
        ChatMessage message = ChatMessage.builder()
            .role(ChatRole.USER.getRoleName())
            .content("Introduce the city of Guangzhou in China")
            .build();
        ChatCompletion chatCompletion = ChatCompletion.builder()
            .model(ChatGptModel.GPT_3_5_TURBO.getModelName())
            .messages(Collections.singletonList(message))
            .stream(true)
            .build();
        client.streamChatCompletion(chatCompletion, eventSourceListener);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void subscription() {
        OpenGptDirectClient client = getClient();
        AccountSubscription accountSubscription = client.querySubscription();
        System.out.println(JsonUtils.toJson(accountSubscription));
    }
    
    @Test
    public void usage() {
        OpenGptDirectClient client = getClient();
        LocalDate startDate = LocalDate.of(2023, 7, 2);
        LocalDate endDate = LocalDate.of(2023, 7, 5);
        AccountBillingUsage usage = client.queryUsage(startDate, endDate);
        System.out.println(JsonUtils.toJson(usage));
    }
    
}
