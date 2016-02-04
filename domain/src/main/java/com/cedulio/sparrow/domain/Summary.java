package com.cedulio.sparrow.domain;

import java.util.Date;

public class Summary {

    private Date dueDate;

    private Date openDate;

    private Date closeDate;

    private double pastBalance;

    private double totalBalance;

    private double interest;

    private double totalCumulative;

    private double paid;

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
