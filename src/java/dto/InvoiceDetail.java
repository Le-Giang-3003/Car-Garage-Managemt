/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author legiang300304
 */
public class InvoiceDetail {

    private String invoiceID;
    private String invoiceDate;
    private String salesID;
    private String salesName;
    private String carID;
    private String serialNumber;
    private String model;
    private String colour;
    private String year;

    public InvoiceDetail() {
    }

    public InvoiceDetail(String invoiceID, String invoiceDate, String salesID, String salesName, String carID, String serialNumber, String model, String colour, String year) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.salesID = salesID;
        this.salesName = salesName;
        this.carID = carID;
        this.serialNumber = serialNumber;
        this.model = model;
        this.colour = colour;
        this.year = year;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
