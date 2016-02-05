package com.cedulio.sparrow.domain;

import java.util.List;

public class Bill {

    private State state;

    private Summary summary;

    private List<Transaction> transactions;

    private String id;

    private String barcode;

    private String linhaDigitavel;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(
            List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }

    public enum State {
        OVERDUE("overdue"),
        CLOSED("closed"),
        OPEN("open"),
        FUTURE("future");

        final String name;

        State(String name) {
            this.name = name;
        }
    }

}
