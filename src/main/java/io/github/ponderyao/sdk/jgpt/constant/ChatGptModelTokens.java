package io.github.ponderyao.sdk.jgpt.constant;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * ChatGptModelTokens
 *
 * @author PonderYao
 * @since 1.0.0
 */
public enum ChatGptModelTokens {

    /**
     * gpt3.5
     */
    GPT_3_5_TURBO(ChatGptModel.GPT_3_5_TURBO, 4, -1, 0),

    /**
     * gpt3.5 support 16k message size
     */
    GPT_3_5_TURBO_16K(ChatGptModel.GPT_3_5_TURBO_16K, 4, -1, 0),

    /**
     * gpt3.5 support function calls
     */
    GPT_3_5_TURBO_0613(ChatGptModel.GPT_3_5_TURBO_0613, 3, 1, 1),

    /**
     * gpt3.5 support function calls and 16k message size
     */
    GPT_3_5_TURBO_16K_0613(ChatGptModel.GPT_3_5_TURBO_16K_0613, 3, 1, 1),

    /**
     * gpt4
     */
    GPT_4(ChatGptModel.GPT_4, 3, 1, 0),

    /**
     * gpt4 support 32k message size
     */
    GPT_4_32K(ChatGptModel.GPT_4_32K, 3, 1, 0),

    /**
     * gpt4 support function calls
     */
    GPT_4_0613(ChatGptModel.GPT_4_0613, 3, 1, 1),

    /**
     * gpt4 support function calls and 16k message size
     */
    GPT_4_32K_0613(ChatGptModel.GPT_4_32K_0613, 3, 1, 1);

    private final ChatGptModel model;
    
    private final Integer messageContentTokens;
    
    private final Integer messageNameTokens;
    
    private final Integer functionCallTokens;

    ChatGptModelTokens(ChatGptModel model, Integer messageContentTokens, Integer messageNameTokens, Integer functionCallTokens) {
        this.model = model;
        this.messageContentTokens = messageContentTokens;
        this.messageNameTokens = messageNameTokens;
        this.functionCallTokens = functionCallTokens;
    }

    public ChatGptModel getModel() {
        return this.model;
    }

    public Integer getMessageContentTokens() {
        return messageContentTokens;
    }

    public Integer getMessageNameTokens() {
        return messageNameTokens;
    }

    public Integer getFunctionCallTokens() {
        return functionCallTokens;
    }

    public static ChatGptModelTokens fromModel(ChatGptModel model) {
        if (Objects.isNull(model)) {
            return null;
        }
        for (ChatGptModelTokens modelTokens : ChatGptModelTokens.values()) {
            if (Objects.equals(modelTokens.getModel(), model)) {
                return modelTokens;
            }
        }
        return null;
    }
    
}
