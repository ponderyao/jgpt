package io.github.ponderyao.sdk.jgpt.domain.chat;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ponderyao.sdk.jgpt.constant.ChatGptModel;

/**
 * ChatCompletion: request parameters for chat completions api
 *
 * @author PonderYao
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatCompletion implements Serializable {
    
    private static final long serialVersionUID = -7061816987903967628L;
    
    private String model = ChatGptModel.GPT_3_5_TURBO.getModelName();
    
    private List<ChatMessage> messages;
    
    private List<ChatFunction> functions;

    /**
     * null - when parameter functions are empty
     * auto - when parameter functions are not empty
     */
    @JsonProperty("function_call")
    private Object functionCall;

    /**
     * 0~2.0, default 1.0
     * a lower value means that the output result is more accurate, while conversely, it is more random
     */
    private Double temperature = 1.0D;

    /**
     * 0~1.0, default 0.9
     * a higher values mean more diverse output results, while conversely, they are more rigorous
     */
    @JsonProperty("top_p")
    private Double topP = 0.9D;

    /**
     * number of chat results generated
     */
    @JsonProperty("n")
    private Integer number = 1;

    /**
     * whether to enable streaming output
     */
    private Boolean stream = false;

    /**
     * text sequence group controlling the termination condition of natural language generation
     */
    private List<String> stop;

    /**
     * the max size of output
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens = 2048;

    /**
     * -2.0~2.0, default 0
     * a higher value means a higher likelihood of discussing new topics, and vice versa
     */
    @JsonProperty("presence_penalty")
    private Double presencePenalty = 0D;

    /**
     * -2.0~2.0, default 0
     * a higher value means a lower probability of the same word appearing, and vice versa
     */
    @JsonProperty("frequency_penalty")
    private Double frequencyPenalty = 0D;

    /**
     * -100~100
     * set the probability of certain keywords appearing when generating text
     */
    @JsonProperty("logit_bias")
    private Map logitBias;

    /**
     * unique identifier that distinguishes endpoint users
     */
    private String user;
    
    public ChatCompletion(Builder builder) {
        this.setModel(builder.model);
        this.setMessages(builder.messages);
        this.setFunctions(builder.functions);
        this.setFunctionCall(builder.functionCall);
        this.setTemperature(builder.temperature);
        this.setTopP(builder.topP);
        this.setNumber(builder.number);
        this.setStream(builder.stream);
        this.setStop(builder.stop);
        this.setMaxTokens(builder.maxTokens);
        this.setPresencePenalty(builder.presencePenalty);
        this.setFrequencyPenalty(builder.frequencyPenalty);
        this.setLogitBias(builder.logitBias);
        this.setUser(builder.user);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static final class Builder {
        private String model = ChatGptModel.GPT_3_5_TURBO.getModelName();
        private List<ChatMessage> messages;
        private List<ChatFunction> functions;
        private Object functionCall;
        private Double temperature = 1.0D;
        private Double topP = 0.9D;
        private Integer number = 1;
        private Boolean stream = false;
        private List<String> stop;
        private Integer maxTokens = 2048;
        private Double presencePenalty = 0D;
        private Double frequencyPenalty = 0D;
        private Map logitBias;
        private String user;
        
        public Builder model(String model) {
            this.model = model;
            return this;
        }
        
        public Builder messages(List<ChatMessage> messages) {
            this.messages = messages;
            return this;
        }
        
        public Builder functions(List<ChatFunction> functions) {
            this.functions = functions;
            return this;
        }
        
        public Builder functionCall(Object functionCall) {
            this.functionCall = functionCall;
            return this;
        }
        
        public Builder temperature(Double temperature) {
            this.temperature = temperature;
            return this;
        }
        
        public Builder topP(Double topP) {
            this.topP = topP;
            return this;
        }
        
        public Builder number(Integer number) {
            this.number = number;
            return this;
        }
        
        public Builder stream(Boolean stream) {
            this.stream = stream;
            return this;
        }
        
        public Builder stop(List<String> stop) {
            this.stop = stop;
            return this;
        }
        
        public Builder maxTokens(Integer maxTokens) {
            this.maxTokens = maxTokens;
            return this;
        }
        
        public Builder presencePenalty(Double presencePenalty) {
            this.presencePenalty = presencePenalty;
            return this;
        }
        
        public Builder frequencyPenalty(Double frequencyPenalty) {
            this.frequencyPenalty = frequencyPenalty;
            return this;
        }
        
        public Builder logitBias(Map logitBias) {
            this.logitBias = logitBias;
            return this;
        }
        
        public Builder user(String user) {
            this.user = user;
            return this;
        }
        
        public ChatCompletion build() {
            return new ChatCompletion(this);
        }
    }
    

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public List<ChatFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<ChatFunction> functions) {
        this.functions = functions;
    }

    public Object getFunctionCall() {
        return functionCall;
    }

    public void setFunctionCall(Object functionCall) {
        this.functionCall = functionCall;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTopP() {
        return topP;
    }

    public void setTopP(Double topP) {
        this.topP = topP;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean isStream() {
        return stream;
    }

    public void setStream(Boolean stream) {
        this.stream = stream;
    }

    public List<String> getStop() {
        return stop;
    }

    public void setStop(List<String> stop) {
        this.stop = stop;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Double getPresencePenalty() {
        return presencePenalty;
    }

    public void setPresencePenalty(Double presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    public Double getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public void setFrequencyPenalty(Double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    public Map getLogitBias() {
        return logitBias;
    }

    public void setLogitBias(Map logitBias) {
        this.logitBias = logitBias;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
