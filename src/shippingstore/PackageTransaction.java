package shippingstore;

import java.io.Serializable;
import java.util.Date;

/**
 * This is the package class
 *
 * @author Zachary Golla, Kentessa Fanfair
 */
public class PackageTransaction implements Serializable {
    private int userId;
    private String trackingNumber;
    private Date shippingDate;
    private Date deliveryDate;
    private float shippingCost;
    private int employeeId;

    public PackageTransaction(int employeeId, String trackingNumber, int userId, Date shippingDate, Date deliveryDate, float shippingCost) {
        this.userId = userId;
        this.trackingNumber = trackingNumber;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
        this.shippingCost = shippingCost;
        this.employeeId = employeeId;
    }

    public String toString() {
        return userId + " " + trackingNumber + " " + shippingDate + " " + deliveryDate +  " " + shippingCost + " "
                + employeeId + "\n";
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
