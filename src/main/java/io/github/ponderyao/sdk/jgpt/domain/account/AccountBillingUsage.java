package io.github.ponderyao.sdk.jgpt.domain.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AccountBillingUsage: usage of account billing
 *
 * @author PonderYao
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBillingUsage implements Serializable {
    
    private static final long serialVersionUID = -216191124441813126L;
    
    private String object;
    
    @JsonProperty("daily_costs")
    private List<AccountDailyCost> dailyCosts;

    /**
     * unit: cent
     */
    @JsonProperty("total_usage")
    private BigDecimal totalUsage;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<AccountDailyCost> getDailyCosts() {
        return dailyCosts;
    }

    public void setDailyCosts(List<AccountDailyCost> dailyCosts) {
        this.dailyCosts = dailyCosts;
    }

    public BigDecimal getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(BigDecimal totalUsage) {
        this.totalUsage = totalUsage;
    }
}
