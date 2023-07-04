package io.github.ponderyao.sdk.jgpt.domain.chat;

import java.io.Serializable;

/**
 * ChatFunctionCall: the function that is expected to be called by ChatGPT
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class ChatFunctionCall implements Serializable {

    private static final long serialVersionUID = 1025106739275827963L;

    /**
     * function name
     */
    private String name;

    /**
     * function parameters
     */
    private String arguments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }
}
