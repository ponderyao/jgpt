# JGPT
**JGPT: 提供OpenAI中ChatGPT相关API接入的Java软件开发工具包**

[![standard-readme compliant](https://img.shields.io/badge/JDK-1.8+-brightgreen.svg?style=flat-square)](https://github.com/ponderyao/agile-decision-engine)
[![standard-readme compliant](https://img.shields.io/badge/Maven-3.8.6-brightgreen.svg?style=flat-square)](https://github.com/ponderyao/agile-decision-engine)
[![standard-readme compliant](https://img.shields.io/badge/OkHttp-4.10.0-pink.svg?style=flat-square)](https://github.com/ponderyao/agile-decision-engine)
[![standard-readme compliant](https://img.shields.io/badge/jtokkit-0.5.0-orange.svg?style=flat-square)](https://github.com/ponderyao/agile-decision-engine)
[![standard-readme compliant](https://img.shields.io/badge/License-Apache2.0-blue.svg?style=flat-square)](https://github.com/ponderyao/agile-decision-engine)

[English Doc](https://github.com/ponderyao/jgpt/blob/main/README.md)

## 简介
JGPT 是专为Java应用提供ChatGPT相关OpenAI能力的SDK，支持直接或流式调用聊天API接口

## 特性
- 提供直接/流式调用的客户端，流式客户端允许调用方逐词打印问询结果
- 直接调用客户端额外提供账户信息与费用记录查询接口，有助于控制使用成本
- 支持GPT-3.5（16k）与GPT-4（32k）等当前OpenAI支持的主流模型
- 支持基于函数调用（function-call)的问询消息内容
- 允许直接访问OpenAI接口受限的应用通过设置自定义代理来访问
- 允许在API调用前后自定义拦截器增加逻辑处理

## 使用
### Maven 依赖
```xml
<dependency>
    <groupId>io.github.ponderyao</groupId>
    <artifactId>jgpt</artifactId>
    <version>1.0.0</version>
</dependency>
```
### Java code
#### 直接调用
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
#### 流式调用
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
#### 查询账户信息
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
#### 查询费用记录
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

## 参考
- [https://github.com/Grt1228/chatgpt-java](https://github.com/Grt1228/chatgpt-java)
- [https://github.com/PlexPt/chatgpt-java](https://github.com/PlexPt/chatgpt-java)