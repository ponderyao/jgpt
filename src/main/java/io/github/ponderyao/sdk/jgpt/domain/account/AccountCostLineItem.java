package io.github.ponderyao.sdk.jgpt.domain.account;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * AccountCostLineItem: account cost item details
 *
 * @author PonderYao
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountCostLineItem implements Serializable {
    
    private static final long serialVersionUID = 7671268007320558572L;

    /**
     * model name
     */
    private String name;

    /**
     * unit: cent
     */
    private BigDecimal cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
