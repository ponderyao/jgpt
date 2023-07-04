package io.github.ponderyao.sdk.jgpt.domain.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Usage
 *
 * @author PonderYao
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usage implements Serializable {

    private static final long serialVersionUID = 4589150683530717867L;

    /**
     * usage of prompt input tokens
     */
    @JsonProperty("prompt_tokens")
    private Long promptTokens;

    /**
     * usage of completion response tokens
     */
    @JsonProperty("completion_tokens")
    private Long completionTokens;

    /**
     * usage of total tokens
     */
    @JsonProperty("total_tokens")
    private Long totalTokens;
    
}
