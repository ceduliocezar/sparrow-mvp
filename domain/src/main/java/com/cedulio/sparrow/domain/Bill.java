package com.cedulio.sparrow.domain;

import java.util.List;

public class Bill {

    private State state;

    private Summary summary;

    private List<Transaction> transactions;

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

    public enum State {
        OVERDUE("overdue"),
        CLOSED("closed"),
        OPEN("open"),
        FUTURE("future");

        final String name;

        State(String name){
            this.name = name;
        }
    }

}
