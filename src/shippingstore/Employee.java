package shippingstore;

import java.io.Serializable;

public class Employee extends User implements Serializable {

    private int socialSecurityNumber;
    private float monthlySalary;
    private int ddBankAccountNumber;

    public Employee(int id, String firstName, String lastName, int socialSecurityNumber, float monthlySalary, int ddBankAccountNumber) {
        super(id, firstName, lastName);
        this.socialSecurityNumber = socialSecurityNumber;
        this.monthlySalary = monthlySalary;
        this.ddBankAccountNumber = ddBankAccountNumber;
    }

    @Override
    public String toString() {
        return (getId() + " " + getFirstName() + " " + getLastName() + " " + socialSecurityNumber + " " + monthlySalary + " " +
                ddBankAccountNumber + "\n" );
    }



    public int getSocialSecurityNumber() { return socialSecurityNumber; }

    public float getMonthlySalary() { return monthlySalary; }

    public int getDdBankAccountNumber() { return ddBankAccountNumber; }



    public void setSocialSecurityNumber(int socialSecurityNumber) { this.socialSecurityNumber = socialSecurityNumber; }

    public void setMonthlySalary(float monthlySalary) { this.monthlySalary = monthlySalary; }

    public void setDdBankAccountNumber(int ddBankAccountNumber) { this.ddBankAccountNumber = ddBankAccountNumber; }
}
