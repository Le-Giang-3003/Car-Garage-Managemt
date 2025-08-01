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
public class Part {

    private String partID;
    private String partName;
    private double purPrice;
    private double retPrice;
    private boolean deleted;

    public Part() {
        this.partID = "";
        this.partName = "";
        this.purPrice = 0.0;
        this.retPrice = 0.0;
        deleted = false;
    }

    public Part(String partID, String partName, double purPrice, double retPrice, boolean deleted) {
        this.partID = partID;
        this.partName = partName;
        this.purPrice = purPrice;
        this.retPrice = retPrice;
        this.deleted = deleted;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(double purPrice) {
        this.purPrice = purPrice;
    }

    public double getRetPrice() {
        return retPrice;
    }

    public void setRetPrice(double retPrice) {
        this.retPrice = retPrice;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Part{" + "partID=" + partID + ", partName=" + partName + ", purPrice=" + purPrice + ", retPrice=" + retPrice + '}';
    }

}
