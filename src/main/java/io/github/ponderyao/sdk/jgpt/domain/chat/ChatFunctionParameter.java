package io.github.ponderyao.sdk.jgpt.domain.chat;

import java.io.Serializable;
import java.util.List;

/**
 * ChatFunctionParameter: the parameter definition in function
 * 
 * ag.
 * <pre>
 * {
 *     "type": "object",
 *     "properties": {
 *         "name": {
 *             "type": "string",
 *             "description": "a Chinese name that matches gender is required"
 *         },
 *         "age": {
 *             "type": "number",
 *             "description": "the human age, between 1 and 100"
 *         },
 *         "gender": {
 *             "type": "string",
 *             "enum": ["male", "female"]
 *         },
 *     },
 *     "required": ["name", "gender"]
 * }
 * </pre>
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class ChatFunctionParameter implements Serializable {

    private static final long serialVersionUID = -2312042147004522084L;

    /**
     * parameter type
     */
    private String type;

    /**
     * define name and description for each parameter
     */
    private Object properties;

    /**
     * name of required parameters
     */
    private List<String> required;
    
    public ChatFunctionParameter(Builder builder) {
        this.setType(builder.type);
        this.setProperties(builder.properties);
        this.setRequired(builder.required);
    }
    
    public static final class Builder {
        private String type;
        private Object properties;
        private List<String> required;
        
        public Builder type(String type) {
            this.type = type;
            return this;
        }
        
        public Builder properties(Object properties) {
            this.properties = properties;
            return this;
        }
        
        public Builder required(List<String> required) {
            this.required = required;
            return this;
        }
        
        public ChatFunctionParameter build() {
            return new ChatFunctionParameter(this);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }
}
