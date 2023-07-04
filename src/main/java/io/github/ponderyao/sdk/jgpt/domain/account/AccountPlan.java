package io.github.ponderyao.sdk.jgpt.domain.account;

import java.io.Serializable;

/**
 * AccountPlan
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class AccountPlan implements Serializable {
    
    private static final long serialVersionUID = -2342107065621031912L;
    
    private String title;
    
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
