package io.github.ponderyao.sdk.jgpt.domain.chat;

import java.io.Serializable;

/**
 * ChatFunction: chat function definition
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class ChatFunction implements Serializable {
    
    private static final long serialVersionUID = 9148941314389991003L;
    
    private String name;
    
    private String description;
    
    private ChatFunctionParameter parameters;
    
    public ChatFunction(Builder builder) {
        this.setName(builder.name);
        this.setDescription(builder.description);
        this.setParameters(builder.parameters);
    }
    
    public static final class Builder {
        private String name;
        private String description;
        private ChatFunctionParameter parameters;
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder parameters(ChatFunctionParameter parameters) {
            this.parameters = parameters;
            return this;
        }
        
        public ChatFunction build() {
            return new ChatFunction(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChatFunctionParameter getParameters() {
        return parameters;
    }

    public void setParameters(ChatFunctionParameter parameters) {
        this.parameters = parameters;
    }
}
