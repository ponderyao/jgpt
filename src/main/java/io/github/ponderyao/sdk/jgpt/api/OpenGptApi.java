package io.github.ponderyao.sdk.jgpt.api;

import io.github.ponderyao.sdk.jgpt.domain.chat.ChatCompletion;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatCompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * OpenGptApi: the open api from OpenAI-ChatGPT
 * 
 * @author PonderYao
 * @since 1.0.0
 */
public interface OpenGptApi {

    /**
     * chat completions api
     *
     * @param chatCompletion chat completions
     * @return chat completions response
     */
    @POST("v1/chat/completions")
    Single<ChatCompletionResponse> chatCompletion(@Body ChatCompletion chatCompletion);
    
}
