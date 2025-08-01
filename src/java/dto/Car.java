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
public class Car {

    private String carID;
    private String serialNumber;
    private String model;
    private String colour;
    private int year;
    private double price;
    private String status;

    public Car() {
        carID = "";
        serialNumber = "";
        model = "";
        colour = "";
        status = "AVAILABLE";
    }

    public Car(String carID, String serialNumber, String model, String colour, int year, double price) {
        this.carID = carID;
        this.serialNumber = serialNumber;
        this.model = model;
        this.colour = colour;
        this.year = year;
        this.price = price;
        this.status = "AVAILABLE";
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Car{" + "carID=" + carID + ", serialNumber=" + serialNumber + ", model=" + model + ", colour=" + colour + ", year=" + year + ", price=" + price + ", status=" + status + '}';
    }
}
