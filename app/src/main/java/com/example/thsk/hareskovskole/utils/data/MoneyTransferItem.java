package com.example.thsk.hareskovskole.utils.data;

import java.io.Serializable;

/**
 * Created by thsk on 29/06/2017.
 */

public class MoneyTransferItem implements Serializable {


    private String groupName;
    private TransactionType transactionType;
    int amount;
    private String id;

    public enum TransactionType {SEND,RECEIVED}

    public MoneyTransferItem(String groupName, TransactionType transactionType, int amount, String id) {
        this.groupName = groupName;
        this.transactionType = transactionType;
        this.amount = amount;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
