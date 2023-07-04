package io.github.ponderyao.sdk.jgpt.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.ModelType;
import io.github.ponderyao.sdk.jgpt.constant.ChatGptModel;
import io.github.ponderyao.sdk.jgpt.constant.ChatGptModelTokens;
import io.github.ponderyao.sdk.jgpt.domain.chat.ChatMessage;
import io.github.ponderyao.sdk.jgpt.exception.TokensCalculationException;

/**
 * UsageTokensUtils: tool to calculate usage tokens
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class UsageTokensUtils {
    
    private static final Map<String, Encoding> modelEncodingMap = new HashMap<>();
    
    private static final EncodingRegistry encodingRegistry = Encodings.newDefaultEncodingRegistry();
    
    static {
        for (ChatGptModel model : ChatGptModel.values()) {
            ModelType.fromName(model.getModelType())
                .ifPresent(modelType -> modelEncodingMap.put(model.getModelName(), encodingRegistry.getEncodingForModel(modelType)));
        }
    }

    /**
     * calculate tokens from text by model
     *
     * @param modelName model name
     * @param text target text
     * @return tokens
     */
    public static int calcTokens(@NotNull String modelName, String text) {
        if (StringUtils.isBlank(text)) {
            return 0;
        }
        Encoding encoding = getEncoding(modelName);
        return encoding.countTokens(text);
    }

    /**
     * calculate tokens from chat messages by model
     * 
     * @param modelName model name
     * @param messages chat messages
     * @return tokens
     */
    public static int calcTokens(@NotNull String modelName, List<ChatMessage> messages) {
        Encoding encoding = getEncoding(modelName);
        
        ChatGptModelTokens modelTokens = ChatGptModelTokens.fromModel(ChatGptModel.fromModelName(modelName));
        if (Objects.isNull(modelTokens)) {
            throw new TokensCalculationException("the model named '%s' used to calculate tokens is non-existent or invalid", modelName);
        }
        
        int totalTokens = 0;
        for (ChatMessage message : messages) {
            totalTokens += encoding.countTokens(message.getRole()) + encoding.countTokens(message.getContent()) + modelTokens.getMessageContentTokens();
            if (StringUtils.isNotBlank(message.getName())) {
                totalTokens += encoding.countTokens(message.getName()) + modelTokens.getMessageNameTokens();
            }
            if (Objects.nonNull(message.getFunctionCall())) {
                totalTokens += encoding.countTokens(message.getFunctionCall().getName()) + modelTokens.getFunctionCallTokens();
                if (StringUtils.isNotBlank(message.getFunctionCall().getArguments())) {
                    totalTokens += encoding.countTokens(message.getFunctionCall().getArguments());
                }
            }
        }
        return totalTokens + 3;
    }

    /**
     * get encoding by model
     * 
     * @param modelName model name
     * @return encoding
     */
    public static Encoding getEncoding(@NotNull String modelName) {
        if (StringUtils.isBlank(modelName)) {
            throw new TokensCalculationException("the name of model used to calculate tokens is empty");
        }
        Encoding encoding = modelEncodingMap.get(modelName);
        if (Objects.isNull(encoding)) {
            throw new TokensCalculationException("the model named '%s' used to calculate tokens is non-existent or invalid", modelName);
        }
        return encoding;
    }
    
}
