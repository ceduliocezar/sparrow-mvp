package com.cedulio.sparrow.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Summary implements Serializable{

    @SerializedName("due_date")
    private Date dueDate;

    @SerializedName("open_date")
    private Date openDate;

    @SerializedName("close_date")
    private Date closeDate;

    @SerializedName("past_balance")
    private double pastBalance;

    @SerializedName("total_balance")
    private double totalBalance;

    @SerializedName("interest")
    private double interest;

    @SerializedName("total_cumulative")
    private double totalCumulative;

    @SerializedName("paid")
    private double paid;

    @SerializedName("minimum_payment")
    private double minimumPayment;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public double getPastBalance() {
        return pastBalance;
    }

    public void setPastBalance(double pastBalance) {
        this.pastBalance = pastBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getTotalCumulative() {
        return totalCumulative;
    }

    public void setTotalCumulative(double totalCumulative) {
        this.totalCumulative = totalCumulative;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getMinimumPayment() {
        return minimumPayment;
    }

    public void setMinimumPayment(double minimumPayment) {
        this.minimumPayment = minimumPayment;
    }
}
