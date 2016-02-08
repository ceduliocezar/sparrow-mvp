package com.cedulio.sparrow.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Bill implements Serializable{

    @SerializedName("state")
    private State state;

    @SerializedName("summary")
    private Summary summary;

    @SerializedName("line_items")
    private List<LineItem> lineItems;

    @SerializedName("id")
    private String id;

    @SerializedName("barcode")
    private String barcode;

    @SerializedName("linha_digitavel")
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

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(
            List<LineItem> lineItems) {
        this.lineItems = lineItems;
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

        @SerializedName("overdue")
        OVERDUE,
        @SerializedName("closed")
        CLOSED,
        @SerializedName("open")
        OPEN,
        @SerializedName("future")
        FUTURE;
    }

}
