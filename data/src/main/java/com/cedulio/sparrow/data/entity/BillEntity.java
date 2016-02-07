package com.cedulio.sparrow.data.entity;

import com.google.gson.annotations.SerializedName;

import com.cedulio.sparrow.domain.Bill;

import java.util.List;


public class BillEntity {

    @SerializedName("state")
    private Bill.State state;

    @SerializedName("summary")
    private SummaryEntity summary;

    @SerializedName("line_items")
    private List<LineItemEntity> lineItems;

    @SerializedName("id")
    private String id;

    @SerializedName("barcode")
    private String barcode;

    @SerializedName("linha_digitavel")
    private String linhaDigitavel;

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

    public List<LineItemEntity> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemEntity> lineItems) {
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
}
