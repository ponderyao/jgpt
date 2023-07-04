package io.github.ponderyao.sdk.jgpt.domain.account;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AccountSubscription: account subscription for billing
 *
 * @author PonderYao
 * @since 1.0.0
 */
public class AccountSubscription implements Serializable {

    private static final long serialVersionUID = 2146425626857697735L;

    private String object;
    
    @JsonProperty("has_payment_method")
    private Boolean hasPaymentMethod;
    
    private Boolean canceled;
    
    @JsonProperty("canceled_at")
    private Object canceledAt;
    
    private Object delinquent;
    
    @JsonProperty("access_until")
    private Long accessUntil;
    
    @JsonProperty("soft_limit")
    private Long softLimit;
    
    @JsonProperty("hard_limit")
    private Long hardLimit;
    
    @JsonProperty("system_hard_limit")
    private Long systemHardLimit;
    
    @JsonProperty("soft_limit_usd")
    private Double softLimitUsd;
    
    @JsonProperty("hard_limit_usd")
    private Double hardLimitUsd;
    
    @JsonProperty("system_hard_limit_usd")
    private Double systemHardLimitUsd;
    
    private AccountPlan plan;
    
    @JsonProperty("account_name")
    private String accountName;
    
    @JsonProperty("po_number")
    private Object poNumber;
    
    @JsonProperty("billing_email")
    private Object billingEmail;
    
    @JsonProperty("tax_ids")
    private Object taxIds;
    
    @JsonProperty("billing_address")
    private Object billingAddress;
    
    @JsonProperty("business_address")
    private Object businessAddress;
    
    private Boolean primary;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Boolean isHasPaymentMethod() {
        return hasPaymentMethod;
    }

    public void setHasPaymentMethod(Boolean hasPaymentMethod) {
        this.hasPaymentMethod = hasPaymentMethod;
    }

    public Boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public Object getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(Object canceledAt) {
        this.canceledAt = canceledAt;
    }

    public Object getDelinquent() {
        return delinquent;
    }

    public void setDelinquent(Object delinquent) {
        this.delinquent = delinquent;
    }

    public Long getAccessUntil() {
        return accessUntil;
    }

    public void setAccessUntil(Long accessUntil) {
        this.accessUntil = accessUntil;
    }

    public Long getSoftLimit() {
        return softLimit;
    }

    public void setSoftLimit(Long softLimit) {
        this.softLimit = softLimit;
    }

    public Long getHardLimit() {
        return hardLimit;
    }

    public void setHardLimit(Long hardLimit) {
        this.hardLimit = hardLimit;
    }

    public Long getSystemHardLimit() {
        return systemHardLimit;
    }

    public void setSystemHardLimit(Long systemHardLimit) {
        this.systemHardLimit = systemHardLimit;
    }

    public Double getSoftLimitUsd() {
        return softLimitUsd;
    }

    public void setSoftLimitUsd(Double softLimitUsd) {
        this.softLimitUsd = softLimitUsd;
    }

    public Double getHardLimitUsd() {
        return hardLimitUsd;
    }

    public void setHardLimitUsd(Double hardLimitUsd) {
        this.hardLimitUsd = hardLimitUsd;
    }

    public Double getSystemHardLimitUsd() {
        return systemHardLimitUsd;
    }

    public void setSystemHardLimitUsd(Double systemHardLimitUsd) {
        this.systemHardLimitUsd = systemHardLimitUsd;
    }

    public AccountPlan getPlan() {
        return plan;
    }

    public void setPlan(AccountPlan plan) {
        this.plan = plan;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Object getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(Object poNumber) {
        this.poNumber = poNumber;
    }

    public Object getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(Object billingEmail) {
        this.billingEmail = billingEmail;
    }

    public Object getTaxIds() {
        return taxIds;
    }

    public void setTaxIds(Object taxIds) {
        this.taxIds = taxIds;
    }

    public Object getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Object billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Object getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(Object businessAddress) {
        this.businessAddress = businessAddress;
    }

    public Boolean isPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }
}
