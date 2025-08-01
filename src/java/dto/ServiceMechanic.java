/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.math.BigDecimal;

/**
 *
 * @author legiang300304
 */
public class ServiceMechanic {

    private String serviceTicketID;
    private String serviceID;
    private String mechanicID;
    private int hours;
    private String comment;
    private BigDecimal rate;

    private String serviceName;
    private String mechanicName;

    public ServiceMechanic() {
    }

    public ServiceMechanic(String serviceTicketID, String serviceID, String mechanicID, int hours, String comment, BigDecimal rate) {
        this.serviceTicketID = serviceTicketID;
        this.serviceID = serviceID;
        this.mechanicID = mechanicID;
        this.hours = hours;
        this.comment = comment;
        this.rate = rate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(String serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getMechanicID() {
        return mechanicID;
    }

    public void setMechanicID(String mechanicID) {
        this.mechanicID = mechanicID;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ServiceMechanic{" + "serviceTicketID=" + serviceTicketID + ", serviceID=" + serviceID + ", mechanicID=" + mechanicID + ", hours=" + hours + ", comment=" + comment + ", rate=" + rate + '}';
    }

}
