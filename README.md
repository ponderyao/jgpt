# JGPT
**JGPT: the Java SDK for OpenAI ChatGPT API**

[![standard-readme compliant](https://img.shields.io/badge/JDK-1.8+-brightgreen.svg?style=flat-square)](https://github.com/ponderyao/agile-decision-engine)
[![standard-readme compliant](https://img.shields.io/badge/Maven-3.8.6-brightgreen.svg?style=flat-square)](https://github.com/ponderyao/agile-decision-engine)
[![standard-readme compliant](https://img.shields.io/badge/OkHttp-4.10.0-pink.svg?style=flat-square)](https://github.com/ponderyao/agile-decision-engine)
[![standard-readme compliant](https://img.shields.io/badge/jtokkit-0.5.0-orange.svg?style=flat-square)](https://github.com/ponderyao/agile-decision-engine)
[![standard-readme compliant](https://img.shields.io/badge/License-Apache2.0-blue.svg?style=flat-square)](https://github.com/ponderyao/agile-decision-engine)

[Chinese Doc](https://github.com/ponderyao/jgpt/blob/main/README_zh.md)

## Introduction
JGPT is an SDK specifically designed to provide ChatGPT related OpenAI capabilities for Java applications, supporting direct or streaming calls to chat API interfaces

## Features
- A client that provides direct/streaming calls, where the latter allows the caller to print query results word by word
- The client for directly calling  provides an additional interface for querying account information and expense records, which helps to control usage costs
- Support mainstream models currently supported by OpenAI, such as GPT-3.5 (16k) and GPT-4 (32k)
- Support inquiry message content based on function call
- Allow applications with limited access to the OpenAI interface to access it directly by setting custom proxies
- Allow custom interceptors to add logical processing before and after API calls

## Use
### Maven dependency
```xml
<dependency>
    <groupId>io.github.ponderyao</groupId>
    <artifactId>jgpt</artifactId>
    <version>1.0.0</version>
</dependency>
```
### Java code
#### direct call
```java
public class JGptTest {
    public void chatCompletion() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
        OpenGptDirectClient client = OpenGptDirectClient.builder()
            .apiKey("sk-**************")  // API_KEY
            .apiHost("https://***.**")    // API_HOST (for proxy)
            .httpClient(httpClient)
            .build();
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
}
```
#### streaming call
```java
public class JGptTest {
    public void chatCompletion() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
        OpenGptDirectClient client = OpenGptDirectClient.builder()
            .apiKey("sk-**************")  // API_KEY
            .apiHost("https://***.**")    // API_HOST (for proxy)
            .httpClient(httpClient)
            .build();
        ChatMessage message = ChatMessage.builder()
            .role(ChatRole.USER.getRoleName())
            .content("Introduce the city of Guangzhou in China")
            .build();
        TestEventSourceListener eventSourceListener = new TestEventSourceListener();
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
}
```
#### account subscription
```java
public class JGptTest {
    public void chatCompletion() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
        OpenGptDirectClient client = OpenGptDirectClient.builder()
            .apiKey("sk-**************")  // API_KEY
            .apiHost("https://***.**")    // API_HOST (for proxy)
            .httpClient(httpClient)
            .build();
        AccountSubscription accountSubscription = client.querySubscription();
        System.out.println(JsonUtils.toJson(accountSubscription));
    }
}
```
#### account usage
```java
public class JGptTest {
    public void chatCompletion() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
        OpenGptDirectClient client = OpenGptDirectClient.builder()
            .apiKey("sk-**************")  // API_KEY
            .apiHost("https://***.**")    // API_HOST (for proxy)
            .httpClient(httpClient)
            .build();
        LocalDate startDate = LocalDate.of(2023, 7, 2);
        LocalDate endDate = LocalDate.of(2023, 7, 5);
        AccountBillingUsage usage = client.queryUsage(startDate, endDate);
        System.out.println(JsonUtils.toJson(usage));
    }
}
```

## Reference
- [https://github.com/Grt1228/chatgpt-java](https://github.com/Grt1228/chatgpt-java)
- [https://github.com/PlexPt/chatgpt-java](https://github.com/PlexPt/chatgpt-java)