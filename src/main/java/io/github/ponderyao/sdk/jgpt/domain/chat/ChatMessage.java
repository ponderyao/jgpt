package io.github.ponderyao.sdk.jgpt.domain.chat;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ChatMessage: define the role and content in dialog
 *
 * @author PonderYao
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 8083034578483943057L;

    /**
     * chat role
     */
    private String role;
    
    private String content;

    /**
     * chat role's name that makes parameter role be omitted
     */
    private String name;
    
    @JsonProperty("function_call")
    private ChatFunctionCall functionCall;
    
    public ChatMessage() {
        
    }
    
    public ChatMessage(Builder builder) {
        this.setRole(builder.role);
        this.setContent(builder.content);
        this.setName(builder.name);
        this.setFunctionCall(builder.functionCall);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static final class Builder {
        private String role;
        private String content;
        private String name;
        private ChatFunctionCall functionCall;
        
        public Builder() {
            
        }
        
        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder functionCall(ChatFunctionCall functionCall) {
            this.functionCall = functionCall;
            return this;
        }

        public ChatMessage build() {
            return new ChatMessage(this);
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatFunctionCall getFunctionCall() {
        return functionCall;
    }

    public void setFunctionCall(ChatFunctionCall functionCall) {
        this.functionCall = functionCall;
    }
}
