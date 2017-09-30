package shippingstore;

public class Customer extends User {

    private String phoneNumber;
    private String Address;

    public String getPhoneNumber() { return phoneNumber; }

    public String getAddress() { return Address; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setAddress(String address) { Address = address; }
}
