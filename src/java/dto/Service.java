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
public class Service {
    private String serviceID;
    private String serviceName;
    private BigDecimal hourlyRate;
    private String status;
    

    public Service() {
        this.serviceID = "";
        this.serviceName = "";
        this.hourlyRate = null;
        this.status = "Active";
    }

    public Service(String serviceID, String serviceName, BigDecimal hourlyRate, String status) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.hourlyRate = hourlyRate;
        this.status = status;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Service{" + "serviceID=" + serviceID + ", serviceName=" + serviceName + ", hourlyRate=" + hourlyRate + ", status=" + status + '}';
    }

    
    
    
}
