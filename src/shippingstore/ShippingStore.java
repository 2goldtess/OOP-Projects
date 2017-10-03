package shippingstore;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
/**
 * This class is used to represent a database interface for a list of
 * <CODE>Package Order</CODE>'s. It using a plain-text file "PackageOrderDB.txt"
 * to store and write package order objects in readable text form. It contains
 * an <CODE>ArrayList</CODE> called <CODE>packageOrerList</CODE> to store the
 * database in a runtime friendly data structure. The <CODE>packageOrerList</CODE>
 * is written to "PackageOrderDB.txt" at the end of the <CODE>ShippingStore</CODE> object's
 * life by calling <CODE>flush()</CODE>. This class also provides methods for
 * adding, remove, and searching for shipping orders from the list.
 *
 * @author Junye Wen
 */
public class ShippingStore {

    private ArrayList<PackageOrder> packageOrderList;
    private ArrayList<Employee> employeesList;
    private ArrayList<Customer> customersList;
    private ArrayList<PackageTransaction> transactionsList;

    final static String DATE_FORMAT = "MM-dd-yyyy";


    /**
     * This constructor is hard-coded to open "<CODE>PackageOrderDB.txt</CODE>" and
     * initialize the <CODE>packageOrerList</CODE> with its contents. If no such file
     * exists, then one is created. The contents of the file are "loaded" into
     * the packageOrerList ArrayList in no particular order. The file is then closed
     * during the duration of the program until <CODE>flush()</CODE> is called.
     * @throws IOException
     */
    public ShippingStore() throws Exception {
        packageOrderList = new ArrayList<>();
        employeesList = new ArrayList<>();
        customersList = new ArrayList<>();

        File dataFile = new File("PackageOrderDB.ser");

        // If data file does not exist, create it.
        if (!dataFile.exists()) {

            try {
                System.out.println("PackageOrderDB.ser does not exist. . .");

                //if the file doesn't exists, create it
                PrintWriter pw = new PrintWriter("PackageOrderDB.ser");
                //close newly created file so we can reopen it
                pw.close();
            } catch (IOException ioe) {

                System.out.println("Problem occurred while trying to access file");
            }


        } else if (dataFile.length() == 0) {

            System.out.println("PackageOrderDB.ser is empty");

        } else {
            // save data to regular text file @TODO REMOVE after through with testing
            PrintWriter pw = new PrintWriter("PackageOrderDB.txt");

            for (PackageOrder c : packageOrderList) {
                pw.print(c.toString());
            }

            pw.close();

            // reading data from file
            try(
                FileInputStream fis = new FileInputStream("PackageOrderDB.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                packageOrderList = (ArrayList<PackageOrder>) ois.readObject();
                customersList = (ArrayList<Customer>) ois.readObject();
                employeesList = (ArrayList<Employee>) ois.readObject();

            } catch (IOException ioe) {
                System.out.println("Problem reading file");
                ioe.printStackTrace();
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Error loading information from the database");
                cnfe.printStackTrace();
            }
        }
    }

    /**
     * Method showPackageOrer displays the current list of package orders in the Arraylist in no
     * particular order.
     *
     */
    public void showPackageOrders() {
        showPackageOrders(packageOrderList);
    }

    /**
     * Private method used as an auxiliary method to display a given ArrayList
     * of package orders in a formatted manner.
     *
     * @param orders the package order list to be displayed.
     */
    private void showPackageOrders(ArrayList<PackageOrder> orders) {

        System.out.println(" -------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Tracking # | Type    | Specification | Class       | Weight(oz) | Volume |                  Other Details              |");
        System.out.println(" ------------------------------------------------------------------------------------------------------------------------- ");

        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getType().equals("Envelope")) {
            System.out.println(String.format("| %-11s| %-8s| %-14s| %-12s| %-11s| %-7s| %-10s     %-10s                   |",
                    orders.get(i).getTrackingNumber(),
                    orders.get(i).getType(),
                    orders.get(i).getSpecification(),
                    orders.get(i).getMailingClass(),
                    String.format("%.2f", orders.get(i).getWeight()),
                    Integer.toString(orders.get(i).getVolume()),
                    "Height: " + Integer.toString(orders.get(i).getEnvelopeHeight()),
                    "Width: " + Integer.toString(orders.get(i).getEnvelopeWidth())));
            }
            else if(orders.get(i).getType().equals("Box"))
                System.out.println(String.format("| %-11s| %-8s| %-14s| %-12s| %-11s| %-7s| %-10s      %-10s               |",
                        orders.get(i).getTrackingNumber(),
                        orders.get(i).getType(),
                        orders.get(i).getSpecification(),
                        orders.get(i).getMailingClass(),
                        String.format("%.2f", orders.get(i).getWeight()),
                        Integer.toString(orders.get(i).getVolume()),
                        "Dimension: " + Integer.toString(orders.get(i).getBoxDimension()),
                        "Volume: " + Integer.toString(orders.get(i).getBoxVolume())));
            else if(orders.get(i).getType().equals("Crate")) {
                System.out.println(String.format("| %-11s| %-8s| %-14s| %-12s| %-11s| %-7s| %-10s     %-20s  |",
                        orders.get(i).getTrackingNumber(),
                        orders.get(i).getType(),
                        orders.get(i).getSpecification(),
                        orders.get(i).getMailingClass(),
                        String.format("%.2f", orders.get(i).getWeight()),
                        Integer.toString(orders.get(i).getVolume()),
                        "Max Weight: " + Float.toString(orders.get(i).getMaxCrateWeight()),
                        "Content: " + orders.get(i).getCrateContent()));
            }
            else if(orders.get(i).getType().equals("Drum")) {
                System.out.println(String.format("| %-11s| %-8s| %-14s| %-12s| %-11s| %-7s| %-14s     %-10s        |",
                        orders.get(i).getTrackingNumber(),
                        orders.get(i).getType(),
                        orders.get(i).getSpecification(),
                        orders.get(i).getMailingClass(),
                        String.format("%.2f", orders.get(i).getWeight()),
                        Integer.toString(orders.get(i).getVolume()),
                        "Material: " + orders.get(i).getDrumMaterial(),
                        "Diameter: " + Float.toString(orders.get(i).getDrumDiameter())));
            }
            else {
                System.out.println(String.format("| %-11s| %-8s| %-14s| %-12s| %-11s| %-7s| %-10s   %-10s|",
                        orders.get(i).getTrackingNumber(),
                        orders.get(i).getType(),
                        orders.get(i).getSpecification(),
                        orders.get(i).getMailingClass(),
                        String.format("%.2f", orders.get(i).getWeight()),
                        Integer.toString(orders.get(i).getVolume()),
                        "No Other Details", "                         "));
            }
        }
        System.out.println(" -------------------------------------------------------------------------------------------------------------------------\n");

    }

    /**
     * This method displays package orders that have a weight within the range of
     * <CODE>low</CODE> to <CODE>high</CODE>.
     *
     * @param low a float that is the lower bound weight.
     * @param high a float that is the upper bound weight.
     */
    public void showPackageOrdersRange(float low, float high) {
        ArrayList<PackageOrder> orders = new ArrayList<>();
        for (PackageOrder order : packageOrderList) {
            if ((low <= order.getWeight()) && (order.getWeight() <= high)) {
                orders.add(order);
            }
        }
        
        if (orders.isEmpty())
            System.out.println("No packages found with weight within the given range.\n");
        else
            showPackageOrders(orders);
    }

    /**
     * This method can be used to find a package order in the Arraylist of orders.
     *
     * @param trackingNumber a <CODE>String</CODE> that represents the tracking number 
     * of the order that to be searched for.
     * @return the <CODE>int</CODE> index of the package orders in the Arraylist of orders,
     * or -1 if the search failed.
     */
    public int findPackageOrder(String trackingNumber) {

        int index = -1;

        for (int i = 0; i < packageOrderList.size(); i++) {
            String temp = packageOrderList.get(i).getTrackingNumber();

            if (trackingNumber.equalsIgnoreCase(temp)) {
                index = i;
                break;
            }
        }

        return index;
    }
    
    /**
     * This method can be used to search for a package order in the Arraylist of orders.
     *
     * @param trackingNumber a <CODE>String</CODE> that represents the tracking number
     * of the order that to be searched for.
     */
    public void searchPackageOrder(String trackingNumber) {

        int index = findPackageOrder(trackingNumber);

        if (index != -1) {
            ArrayList<PackageOrder> order = new ArrayList<>(1);
            order.add(getPackageOrder(index));
            System.out.println("\nHere is the order that matched:\n");
            showPackageOrders(order);
        } else {
            System.out.println("\nSearch did not find a match.\n");
        }
    }


    /**
     * This method is used to add a package order to the orderList ArrayList. In order for a
     * package order to be added to the ArrayList it must comply with the following:
     * <p>
     * 1. The order is not already in the ArrayList according to the tracking number
     * as the unique key.
     * <p>
     * 2. The TrackningNumber string matches the following regular expression:
     * <CODE>"[A-Za-z0-9]{5}"</CODE> or in other words: it
     * is 5 avinhanumeric characters.
     * <p>
     * 3. The Type of the order can be only one of the following:
     *    Postcard, Letter, Envelope, Packet, Box, Crate, Drum, Roll, Tube.
     * <p>
     * 4. The Specification of the order can be only one of the following:
     *    Fragile, Books, Catalogs, Do-not-Bend, N/A.
     * <p>
     * 5. The Mailing Class of the order can be only one of the following:
     *    First-Class, Priority, Retail, Ground, Metro.
     * <p>
     * 6. The Weight must be non-negative.
     * <p>
     * 7. The Volume must be non-negative.
     * @param 'toAdd' the <CODE>PackageOrder</CODE> object to add to the
     * <CODE>packageOrderList</CODE>
     */
    public void addOrder(String trackingNumber, String type, String specification, String mailingClass, String weight, String volume) {

        if (this.findPackageOrder(trackingNumber) != -1) {
            System.out.println("Package Order already exists in database. \n");
            return;
        }

        if (!trackingNumber.matches("[A-Za-z0-9]{5}")) {
            System.out.println("Invalid Tracking Number: not proper format."
                + "Tracking Number must be at least 5 alphanumeric characters.");
            return;
        }

        if (!(type.equals("Postcard") || type.equals("Letter") || type.equals("Envelope")
            || type.equals("Packet") || type.equals("Box")|| type.equals("Crate")
            || type.equals("Drum")|| type.equals("Roll")|| type.equals("Tube"))) {
            System.out.println("Invalid type:\n"
                + "Type must be one of following: "
                + "Postcard, Letter, Envelope, Packet, Box, Crate, Drum, Roll, Tube.");
            return;
        }

        if (!(specification.equals("Fragile") || specification.equals("Books") || specification.equals("Catalogs")
            || specification.equals("Do-not-Bend") || specification.toUpperCase().equals("N/A"))) {
            System.out.println("Invalid specification:\n"
                + "Specification must be one of following: "
                + "Fragile, Books, Catalogs, Do-not-Bend, N/A.");
            return;
        }

        if (!(mailingClass.equals("First-Class") || mailingClass.equals("Priority") || mailingClass.equals("Retail")
            || mailingClass.equals("Ground") || mailingClass.equals("Metro")) ) {
            System.out.println("Invalid Mailing Class:\n"
                + "Mailing Class must be one of following: "
                + "First-Class, Priority, Retail, Ground, Metro.");
            return;
        }

        if (Float.parseFloat(weight) < 0) {
            System.out.println("The weight of package cannot be negative.");
            return;
        }

        if (!volume.matches("[0-9]{1,6}")) {
            System.out.println("Invalid volume:\n"
                + "The package's volume has to be an integer number between 0 and 999999. ");
            return;
        }

        //If passed all the checks, add the order to the list
        packageOrderList.add(new PackageOrder(trackingNumber, type, specification, mailingClass,
                Float.parseFloat(weight), Integer.parseInt(volume)));
        System.out.println("Package Order has been added.\n");
    }

    public void addOrder(String trackingNumber, String type, String specification, String mailingClass, String weight, String volume, String detailOne, String detailTwo) {

        if (this.findPackageOrder(trackingNumber) != -1) {
            System.out.println("Package Order already exists in database. \n");
            return;
        }

        if (!trackingNumber.matches("[A-Za-z0-9]{5}")) {
            System.out.println("Invalid Tracking Number: not proper format."
                    + "Tracking Number must be at least 5 alphanumeric characters.");
            return;
        }

        if (!(type.equals("Postcard") || type.equals("Letter") || type.equals("Envelope")
                || type.equals("Packet") || type.equals("Box")|| type.equals("Crate")
                || type.equals("Drum")|| type.equals("Roll")|| type.equals("Tube"))) {
            System.out.println("Invalid type:\n"
                    + "Type must be one of following: "
                    + "Postcard, Letter, Envelope, Packet, Box, Crate, Drum, Roll, Tube.");
            return;
        }

        if (!(specification.equals("Fragile") || specification.equals("Books") || specification.equals("Catalogs")
                || specification.equals("Do-not-Bend") || specification.toUpperCase().equals("N/A"))) {
            System.out.println("Invalid specification:\n"
                    + "Specification must be one of following: "
                    + "Fragile, Books, Catalogs, Do-not-Bend, N/A.");
            return;
        }

        if (!(mailingClass.equals("First-Class") || mailingClass.equals("Priority") || mailingClass.equals("Retail")
                || mailingClass.equals("Ground") || mailingClass.equals("Metro")) ) {
            System.out.println("Invalid Mailing Class:\n"
                    + "Mailing Class must be one of following: "
                    + "First-Class, Priority, Retail, Ground, Metro.");
            return;
        }

        if (Float.parseFloat(weight) < 0) {
            System.out.println("The weight of package cannot be negative.");
            return;
        }

        if (!volume.matches("[0-9]{1,6}")) {
            System.out.println("Invalid volume:\n"
                    + "The package's volume has to be an integer number between 0 and 999999. ");
            return;
        }

        //If passed all the checks, add the order to the list
        if(type.equals("Envelope") || (type.equals("Box"))) {
            packageOrderList.add(new PackageOrder(trackingNumber, type, specification, mailingClass,
                    Float.parseFloat(weight), Integer.parseInt(volume), Integer.parseInt(detailOne), Integer.parseInt(detailTwo)));
        }
        else if(type.equals("Crate")) {
            packageOrderList.add(new PackageOrder(trackingNumber, type, specification, mailingClass,
                    Float.parseFloat(weight), Integer.parseInt(volume), Float.parseFloat(detailOne), detailTwo));
        }
        else {
            packageOrderList.add(new PackageOrder(trackingNumber, type, specification, mailingClass,
                    Float.parseFloat(weight), Integer.parseInt(volume), detailOne, Float.parseFloat(detailTwo)));
        }
    }
    /**
     * This method will remove an order from the <CODE>packageOrderList</CODE> ArrayList. It
     * will remove the instance of an order that matches tracking number that was
     * passed to this method. If no such order exists, it will produce an error message.
     *
     * @param 'toDelete' the <CODE>PackageOrder</CODE> object to be removed.
     */
    public void removeOrder(String trackingNum) {
        int orderID = findPackageOrder(trackingNum);
        if (orderID == -1) {
            System.out.println("\nAction failed. No package order with the given tracking # exist in database.\n");
        }
        else {
            packageOrderList.remove(orderID);
            System.out.println("\nAction successful. Package order has been removed from the database.\n");
        }
    }

    /**
     * This method is used to retrieve the PackageOrder object from the
     * <CODE>PackageOrderList</CODE> at a given index.
     *
     * @param i the index of the desired <CODE>PackageOrder</CODE> object.
     * @return the <CODE>PackageOrder</CODE> object at the index or null if the index is
     * invalid.
     */
    public PackageOrder getPackageOrder(int i) {
        if (i < packageOrderList.size() && i >= 0) {
            return packageOrderList.get(i);
        } else {
            System.out.println("Invalid Index. Please enter another command or 'h' to list the commands.");
            return null;
        }
    }

    /**
     * This method opens <CODE>"PackageOrderDB.txt"</CODE> and overwrites it with a text representation of
     * all the package orders in the <CODE>PackageOrderList</CODE>.
     * This should be the last method to be called before exiting the program.
     * @throws IOException
     */
    public void flush() {

        try(
                FileOutputStream fos = new FileOutputStream("PackageOrderDB.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {

            // serializing packages data
            oos.writeObject(packageOrderList);
            // serializing customer data
            oos.writeObject(customersList);
            // serializing employee data
            oos.writeObject(employeesList);

            // save data to regular text file @REMOVE after through with debugging
            PrintWriter pw = new PrintWriter("PackageOrderDB.txt");

            pw.printf("Packages%n-----------------------------------------------------------------------%n");
            for (PackageOrder p : packageOrderList) {
                pw.print(p.toString());
            }

            pw.printf("%n%n%nCustomers%n-----------------------------------------------------------------------%n");
            for (Customer c: customersList){
                pw.print(c);
            }

            pw.printf("%n%n%nEmployees%n-----------------------------------------------------------------------%n");
            for (Employee e: employeesList){
                pw.print(e);
            }
            pw.close();

        } catch (IOException ioe) {
            System.out.println("Problem occurred while saving packages");
            ioe.printStackTrace();
        }
    }

    public void addNewUser(String userType) {

        if (!(userType.equals("Employee") || userType.equals("Customer"))) {
            System.out.printf("Invalid User:%n"
                    + "User must be one of following: "
                    + "Employee, Customer");
            return;
        }

        Scanner userInput = new Scanner(System.in);

        if (userType.equals("Employee")) {
            System.out.printf("Please type the employee info with the following pattern: %n%n" +
                              "FIRSTNAME LASTNAME SSN MONTHLY-SALARY DIRECT-DEPOSIT-BANK-NUMBER %n" +
                              "   example: %n" +
                              "   John Smith 1123334545 2300 1011120000%n");

            // Get employee info from inputs stream
            String inputStream = userInput.nextLine().trim();
            System.out.println();
            String[] temp = inputStream.split(" ");

            // Validate employee, if invalid return to menu
            if (isValidEmployeeInfo(temp) == false) {
                return;
            }

            // If passed all the checks, add the employee to the employeeList
            int ssn = Integer.parseInt(temp[2]);
            float salary = Float.parseFloat(temp[3]);
            int ddBA = Integer.parseInt(temp[4]);

            employeesList.add(new Employee(employeesList.size()+1, temp[0], temp[1], ssn, salary, ddBA));
        }

        if (userType.equals("Customer")) {
            System.out.printf( "Please type the customer info with the following pattern:%n%n" +
                               "FIRSTNAME LASTNAME PHONE-NUMBER%n" +
                               "   example:%n" +
                               "   John Smith 222-443-4523%n");


            // Get customer name and number from input stream
            String inputStream = userInput.nextLine();
            System.out.println();
            String[] temp = inputStream.split(" ");

            // Validate customer info, if invalid return to menu
            if (isValidateCustomerNameandNumber(temp) == false) {
                return;
            }

            // Get customer address
            System.out.printf("Please type the customer street address with the following pattern:%n%n" +
                              "STREET# STREET-NAME APT# (if applicable) ZIPCODE%n" +
                              "   example: %n" +
                              "   123 Easy St, 78666 TX%n" );
            String temp2 = userInput.nextLine().trim();

            // Validate customer address
            if (isValidateCustomerAddress(temp2) == false) {
                return;
            }

            //If info entered passed all the checks, add the user to the customersList
            customersList.add(new Customer(customersList.size()+1,temp[0], temp[1], temp[2], temp2));
        }
    }

    void validateEmployeeInfo(String[] temp) {

    }

    boolean isValidateCustomerNameandNumber(String[] temp) {

        // TODO validate names

        // Validate phone number
        if((temp[2]).matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")){
            return true;
        }

        System.out.printf("Invalid Phone Number:%n"
                + "Phone number must be of the following format: %n"
                + "555-123-4567%n%n");
        return false;
    }

    boolean isValidateCustomerAddress(String address) {

        if (address.matches("[\\\\\\d]+[A-Za-z0-9\\\\\\s,\\\\.]+?[\\\\\\d\\\\]+[\\\\\\s]+[a-zA-Z]+")) {
            return true;
        }
        System.out.printf("Invalid Address:%n" +
                "Address must be of the following format: %n" +
                "123 Easy St, 78666 TX%n%n");
        return false;
    }

    boolean isValidEmployeeInfo(String[] temp) {
        // TODO validate info
        return true;
    }

    public boolean findEmployee(int employeeID) {
        for (int i = 0; i < employeesList.size(); i++) {
            if (employeeID == employeesList.get(i).getId())
                return true;
        }
        return false;
    }

    public boolean findUser(int userId) {
        for (int i = 0; i < customersList.size(); i++) {
            if (userId == customersList.get(i).getId())
                return true;
        }
        return false;
    }

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public void addTransaction(String employeeNumber, String trackingNumber, String userId, String shippingDate, String deliveryDate, String costOfShipping) {
        DateFormat dfsd = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat dfdd = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dfsd.parse(shippingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = dfdd.parse(deliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        transactionsList.add(new PackageTransaction(Integer.parseInt(employeeNumber), trackingNumber, Integer.parseInt(userId), startDate,
                endDate, Float.parseFloat(costOfShipping)));
    }
}
