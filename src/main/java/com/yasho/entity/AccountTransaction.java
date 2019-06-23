package com.yasho.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountTransaction implements Comparable<AccountTransaction> {

    private LocalDate transactionDate;
    private BigDecimal amountTransfered;
    private Account transferedTo;

    public AccountTransaction(LocalDate transactionDate, BigDecimal amountTransfered, Account transferedTo) {
        this.transactionDate = transactionDate;
        this.amountTransfered = amountTransfered;
        this.transferedTo = transferedTo;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmountTransfered() {
        return amountTransfered;
    }

    public void setAmountTransfered(BigDecimal amountTransfered) {
        this.amountTransfered = amountTransfered;
    }

    public Account getTransferedTo() {
        return transferedTo;
    }

    public void setTransferedTo(Account transferedTo) {
        this.transferedTo = transferedTo;
    }

    @Override
    public int compareTo(AccountTransaction o) {
        if(o == null) {
            return 1;
        }
        if(this.transactionDate.isAfter(o.transactionDate)) {
            return 1;
        } else if(this.transactionDate.isBefore(o.transactionDate)) {
            return -1;
        }
        return 0;
    }
}
