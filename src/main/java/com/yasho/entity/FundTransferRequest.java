package com.yasho.entity;

import java.math.BigDecimal;

public class FundTransferRequest {
    private String sourceAccountId;
    private String destinationAccountId;
    private BigDecimal amount;

    public FundTransferRequest() {
    }

    public FundTransferRequest(String sourceAccountId, String destinationAccountId, BigDecimal amount) {
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
