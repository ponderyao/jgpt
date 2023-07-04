package io.github.ponderyao.sdk.jgpt.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * ChatGptModel: the optional models of ChatGPT
 *
 * @author PonderYao
 * @since 1.0.0
 */
public enum ChatGptModel {

    /**
     * gpt3.5
     */
    GPT_3_5_TURBO("gpt-3.5-turbo", "gpt-3.5-turbo"),

    /**
     * gpt3.5 support 16k message size
     */
    GPT_3_5_TURBO_16K("gpt-3.5-turbo-16k", "gpt-3.5-turbo"),

    /**
     * gpt3.5 support function calls
     */
    GPT_3_5_TURBO_0613("gpt-3.5-turbo-0613", "gpt-3.5-turbo"),

    /**
     * gpt3.5 support function calls and 16k message size
     */
    GPT_3_5_TURBO_16K_0613("gpt-3.5-turbo-16k-0613", "gpt-3.5-turbo"),

    /**
     * gpt4
     */
    GPT_4("gpt-4", "gpt-4"),

    /**
     * gpt4 support 32k message size
     */
    GPT_4_32K("gpt-4-32k", "gpt-4-32k"),

    /**
     * gpt4 support function calls
     */
    GPT_4_0613("gpt-4-0613", "gpt-4"),
    
    /**
     * gpt4 support function calls and 16k message size
     */
    GPT_4_32K_0613("gpt-4-32k-0613", "gpt-4-32k");

    private final String modelName;
    
    private final String modelType;

    ChatGptModel(String modelName, String modelType) {
        this.modelName = modelName;
        this.modelType = modelType;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelType() {
        return modelType;
    }
    
    public static ChatGptModel fromModelName(String modelName) {
        if (StringUtils.isBlank(modelName)) {
            return null;
        }
        for (ChatGptModel model : ChatGptModel.values()) {
            if (StringUtils.equals(model.getModelName(), modelName)) {
                return model;
            }
        }
        return null;
    }
    
}
