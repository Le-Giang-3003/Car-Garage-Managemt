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
public class Mechanic {
    private String mechanicID;
    private String mechanicName;
    
    public Mechanic(){
        
    }

    public Mechanic(String mechanicID, String mechanicName) {
        this.mechanicID = mechanicID;
        this.mechanicName = mechanicName;
    }

    public String getMechanicID() {
        return mechanicID;
    }

    public void setMechanicID(String mechanicID) {
        this.mechanicID = mechanicID;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    @Override
    public String toString() {
        return "Mechanic{" + "mechanicID=" + mechanicID + ", mechanicName=" + mechanicName + '}';
    }

    
    
}
