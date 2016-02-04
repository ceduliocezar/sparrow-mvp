package com.cedulio.sparrow.data.entity;

import com.google.gson.annotations.SerializedName;

import com.cedulio.sparrow.domain.Bill;
import com.cedulio.sparrow.domain.Summary;
import com.cedulio.sparrow.domain.Transaction;

import java.util.List;


public class BillEntity {

    @SerializedName("state")
    private Bill.State state;

    @SerializedName("summary")
    private SummaryEntity summary;

    @SerializedName("line_items")
    private List<TransactionEntity> transactions;

    public Bill.State getState() {
        return state;
    }


    public void setState(Bill.State state) {
        this.state = state;
    }

    public SummaryEntity getSummary() {
        return summary;
    }

    public void setSummary(SummaryEntity summary) {
        this.summary = summary;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(
            List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
}
