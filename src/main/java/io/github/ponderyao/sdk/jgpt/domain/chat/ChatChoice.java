package io.github.ponderyao.sdk.jgpt.domain.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ChatChoice: the possible choice of ChatGPT response
 *
 * @author PonderYao
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatChoice {
    
    private Long index;

    /**
     * data for one-time response
     */
    @JsonProperty("message")
    private ChatMessage message;

    /**
     * data for stream response
     */
    @JsonProperty("delta")
    private ChatMessage delta;

    /**
     * data for function-call response
     */
    @JsonProperty("finish_reason")
    private String finishReason;

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public ChatMessage getDelta() {
        return delta;
    }

    public void setDelta(ChatMessage delta) {
        this.delta = delta;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }
}
