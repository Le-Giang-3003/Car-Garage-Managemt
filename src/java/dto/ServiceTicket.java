/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;


/**
 *
 * @author legiang300304
 */
public class ServiceTicket {
    private String serviceTicketID;
    private Date dateReceived; 
    private Date dateReturned;
    private String custID;
    private String carID;

    public ServiceTicket() {
    }

    public ServiceTicket(String serviceTicketID, Date dateReceived, Date dateReturned, String custID, String carID) {
        this.serviceTicketID = serviceTicketID;
        this.dateReceived = dateReceived;
        this.dateReturned = dateReturned;
        this.custID = custID;
        this.carID = carID;
    }

    public String getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(String serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    @Override
    public String toString() {
        return "ServiceTicket{" + "serviceTicketID=" + serviceTicketID + ", dateReceived=" + dateReceived + ", dateReturned=" + dateReturned + ", custID=" + custID + ", carID=" + carID + '}';
    }
    
    
}
