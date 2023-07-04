package io.github.ponderyao.sdk.jgpt.constant;

/**
 * ChatRole: the roles of chat message
 *
 * @author PonderYao
 * @since 1.0.0
 */
public enum ChatRole {
    
    SYSTEM("system"),
    
    USER("user"),

    ASSISTANT("assistant"),
    
    FUNCTION("function");
    
    private final String roleName;
    
    ChatRole(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
}
