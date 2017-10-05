package shippingstore;

import java.io.Serializable;

/**
 * This is the customer class
 *
 * @author Zachary Golla, Kentessa Fanfair
 */
public class Customer extends User implements Serializable {

    private String phoneNumber;
    private String address;

    public Customer(int id, String firstName, String lastName, String phoneNumber, String address) {
        super(id, firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    @Override
    public String toString() {
        return (getId() + " " + getFirstName() + " " + getLastName() + " " + phoneNumber + " " + address + "\n");
    }

    public String getPhoneNumber() { return phoneNumber; }

    public String getAddress() { return address; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setAddress(String address) { this.address = address; }




}
