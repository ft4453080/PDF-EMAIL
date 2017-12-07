package com.highrock.pdf.bean;

import java.util.Date;

/**
 * Created by user on 2017/12/4.
 */
public class Invoice {
    public String getSoldName() {
        return soldName;
    }

    public void setSoldName(String soldName) {
        this.soldName = soldName;
    }

    public String getSoldAddress() {
        return soldAddress;
    }

    public void setSoldAddress(String soldAddress) {
        this.soldAddress = soldAddress;
    }

    public String getSoldEmail() {
        return soldEmail;
    }

    public void setSoldEmail(String soldEmail) {
        this.soldEmail = soldEmail;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    private String soldName;
    private String soldAddress;
    private String soldEmail;
    private String invoiceDate;
    private String invoiceNumber;
    private String orderPrice;
    private String tax;
    private String total;
    private String orderNumber;
    private String styleName;
    private String description;
    private String amount;

}
