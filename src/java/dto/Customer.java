/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author legiang300304
 */
public class Customer implements Serializable{
    private String custID;
    private String custName;
    private String phone;
    private String sex;
    private String cusAddress;
    private String status;

    public Customer() {
        this.custID = "";
        this.custName = "";
        this.phone = "";
        this.sex = "";
        this.cusAddress = "";
        this.status = "Active";
    }

    public Customer(String custID, String custName, String phone, String sex, String cusAddress, String status) {
        this.custID = custID;
        this.custName = custName;
        this.phone = phone;
        this.sex = sex;
        this.cusAddress = cusAddress;
        this.status = status;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" + "custID=" + custID + ", custName=" + custName + ", phone=" + phone + ", sex=" + sex + ", cusAddress=" + cusAddress + ", status=" + status + '}';
    }
    
    
}

   