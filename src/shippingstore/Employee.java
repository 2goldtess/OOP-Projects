package shippingstore;

public class Employee extends User{

    private int socialSecurityNumber;
    private float monthlySalary;
    private int ddBankAccountNumber;

    public int getSocialSecurityNumber() { return socialSecurityNumber; }

    public float getMonthlySalary() { return monthlySalary; }

    public int getDdBankAccountNumber() { return ddBankAccountNumber; }



    public void setSocialSecurityNumber(int socialSecurityNumber) { this.socialSecurityNumber = socialSecurityNumber; }

    public void setMonthlySalary(float monthlySalary) { this.monthlySalary = monthlySalary; }

    public void setDdBankAccountNumber(int ddBankAccountNumber) { this.ddBankAccountNumber = ddBankAccountNumber; }
}
