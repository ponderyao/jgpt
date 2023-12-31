package io.github.ponderyao.sdk.jgpt.domain.chat;

import java.io.Serializable;
import java.util.List;

import io.github.ponderyao.sdk.jgpt.domain.common.Usage;

/**
 * ChatCompletionResponse: the response from ChatCPT chat completions
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class ChatCompletionResponse implements Serializable {
    
    private static final long serialVersionUID = -8528772274853288633L;

    private String id;
    
    private String object;
    
    private Long created;

    /**
     * the ChatGPT model
     */
    private String model;
    
    private List<ChatChoice> choices;
    
    private Usage usage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<ChatChoice> choices) {
        this.choices = choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
}
