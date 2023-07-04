package io.github.ponderyao.sdk.jgpt.listener;

import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.ponderyao.sdk.jgpt.domain.chat.ChatChoice;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatCompletionResponse;
import io.github.ponderyao.sdk.jgpt.util.JsonUtils;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * AbstractStreamESListener: abstract event source listener for stream response
 *
 * @author PonderYao
 * @since 1.0.0
 */
public abstract class AbstractStreamESListener extends EventSourceListener {

    public static final Logger log = LoggerFactory.getLogger(AbstractStreamESListener.class);
    
    @Override
    public void onEvent(@NotNull EventSource eventSource, String id, String type, @NotNull String data) {
        log.error("data: {}", data);
        if (data.equals("[DONE]")) {
            return;
        }
        ChatCompletionResponse response = JsonUtils.fromJson(data, ChatCompletionResponse.class);
        if (Objects.nonNull(response)) {
            List<ChatChoice> choices = response.getChoices();
            if (Objects.nonNull(choices) && !choices.isEmpty()) {
                String delta = choices.get(0).getDelta().getContent();
                processEvent(delta);
            }
        }
    }
    
    @Override
    public void onFailure(@NotNull EventSource eventSource, Throwable throwable, Response response) {
        log.error("error: {}", (Objects.isNull(throwable) ? null : throwable.getMessage()));
        if (Objects.isNull(response)) {
            eventSource.cancel();
            return;
        }
        try (ResponseBody responseBody = response.body()) {
            if (Objects.nonNull(responseBody)) {
                String errorMessage = responseBody.string();
                log.error("open api request failed: {}", errorMessage);
                this.processError(throwable, errorMessage);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            eventSource.cancel();
        }
    }
    
    public abstract void processEvent(String eventMessage);
    
    public abstract void processError(Throwable error, String errorMessage);
    
}
