package io.github.ponderyao.sdk.jgpt.domain.account;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AccountDailyCost: account cost details
 *
 * @author PonderYao
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDailyCost implements Serializable {
    
    private static final long serialVersionUID = -3356370207114189367L;
    
    private Long timestamp;
    
    @JsonProperty("line_times")
    private List<AccountCostLineItem> lineTimes;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public List<AccountCostLineItem> getLineTimes() {
        return lineTimes;
    }

    public void setLineTimes(List<AccountCostLineItem> lineTimes) {
        this.lineTimes = lineTimes;
    }
}
