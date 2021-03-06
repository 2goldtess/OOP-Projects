package shippingstore;

import java.io.Serializable;

/**
 * This is the user class
 *
 * @author Zachary Golla, Kentessa Fanfair
 */
public class User implements Serializable {

    private int id;
    private String firstName;
    private String lastName;

    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return (id + " " + firstName + " " + lastName);
    }

    public int getId() { return id; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public void setId(int id) { this.id = id; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }
}
