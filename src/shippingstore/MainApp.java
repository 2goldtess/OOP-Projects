package shippingstore;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main class of the ShippingStore database manager. It provides a
 * console for a user to use the 5 main commands.
 *
 * @author Junye Wen
 */
public class MainApp {

    /**
     * This method will begin the user interface console. Main uses a loop to
     * continue doing commands until the user types '6'. A lot of user input
     * validation is done in the loop. At least enough to allow the interface
     * with ShippingStore to be safe.
     *
     * @param args this program expects no command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        ShippingStore shippingstore = new ShippingStore();

        String welcomeMessage = "\nWelcome to the Shipping Store database. Choose one of the following functions:\n\n"
                + "\t1.  Show all existing package orders in the database\n"
                + "\t2.  Add a new package order to the database.\n"
                + "\t3.  Delete a package order from a database.\n"
                + "\t4.  Search for a package order (given its Tracking #).\n"
                + "\t5.  Show a list of orders within a given weight range.\n"
                + "\t6.  Add new user to the database.\n"
                + "\t7.  Update user info.\n"
                + "\t8.  Complete a shipping transaction.\n"
                + "\t9.  Show completed shipping transactions.\n"
                + "\t0.  Exit program.\n";

        System.out.println(welcomeMessage);

        int selection = in.next().charAt(0);
        in.nextLine();

        while (selection != '0') {

            switch (selection) {
                case '1':
                    shippingstore.showPackageOrders();
                    break;
                case '2':

                    boolean inputGood = false;

                    while (!inputGood) {
                        System.out.println("\nPlease type the following type of package that will be sent from the list of choices: \nPostcard, Letter, Envelope, Packet, Box, Crate, Drum, Roll, Tube.");
                        String initialinput = in.nextLine();

                        switch (initialinput) {
                            case "Postcard":
                                System.out.println("\nPlease type description of package with the following pattern:\n"
                                        + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME\n"
                                        + "example:\nGFR23 Postcard Do-not-Bend Ground 1 2\n");
                                inputGood = true;
                                break;
                            case "Letter":
                                System.out.println("\nPlease type description of package with the following pattern:\n"
                                        + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME\n"
                                        + "example:\nZK4J3 Letter Do-not-Bend First-Class 2 1\n");
                                inputGood = true;
                                break;
                            case "Packet":
                                System.out.println("\nPlease type description of package with the following pattern:\n"
                                        + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME\n"
                                        + "example:\n89BH3 Packet Fragile Metro 4 3\n");
                                inputGood = true;
                                break;
                            case "Tube":
                                System.out.println("\nPlease type description of package with the following pattern:\n"
                                        + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME\n"
                                        + "example:\nKI8Y8 Tube N/A Priority 15 24\n");
                                inputGood = true;
                                break;
                            case "Box":
                                System.out.println("\nPlease type description of package with the following pattern:\n"
                                        + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME   LARGEST_DIMENSION   VOLUME\n"
                                        + "example:\nGU633 Box Books Retail 9500.0 45 50 45\n");
                                inputGood = true;
                                break;
                            case "Crate":
                                System.out.println("\nPlease type description of package with the following pattern:\n"
                                        + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME   MAX_LOAD_WEIGHT   CONTENT\n"
                                        + "example:\n83RS9 Crate Books Ground 55.0 78 120 Textbooks\n");
                                inputGood = true;
                                break;
                            case "Drum":
                                System.out.println("\nPlease type description of package with the following pattern:\n"
                                        + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME   MATERIAL   DIAMETER\n"
                                        + "example:\n92IA3 Drum Fragile Metro 2.35 12 Plastic 12.5\n");
                                inputGood = true;
                                break;
                            case "Envelope":
                                System.out.println("\nPlease type description of package with the following pattern:\n"
                                        + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME   HEIGHT   WIDTH\n"
                                        + "example:\nH43SM Envelope N/A Priority 3.0 4 1 5\n");
                                inputGood = true;
                                break;
                            case "Roll":
                                System.out.println("\nPlease type description of package with the following pattern:\n"
                                        + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME\n"
                                        + "example:\nKN78B Roll N/A Priority 15 24\n");
                                inputGood = true;
                                break;
                            default:
                                System.out.println("\nImproper input.");
                                break;
                        }
                    }
                    String inTemp = in.nextLine();

                    String temp[] = inTemp.split(" ");

                    if (temp.length != 6) {
                        shippingstore.addOrder(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);
                    } else {
                        shippingstore.addOrder(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
                    }
                    break;
                case '3':
                    shippingstore.showPackageOrders();

                    System.out.println("\nPlease enter the tracking # of the package order to delete from the database.\n");
                    String orderToDelete = in.nextLine();
                    shippingstore.removeOrder(orderToDelete);
                    break;
                case '4':
                    System.out.println("\nEnter the Tracking # of the order you wish to see.\n");
                    String trackingNum = in.next();
                    in.nextLine();
                    shippingstore.searchPackageOrder(trackingNum);
                    break;
                case '5':
                    float high = 0;
                    float low = 0;
                    
                    System.out.println("\nEnter lower-bound weight.\n");
                    low = in.nextFloat();
                    System.out.println("\nEnter upper-bound weight.\n");
                    high = in.nextFloat();
                    in.nextLine();
                    shippingstore.showPackageOrdersRange(low, high);
                    break;
                case '6':
                    System.out.println("\nPlease enter the type of user to add to the database");
                    String userType = in.next().trim();
                    shippingstore.addNewUser(userType);
                    break;
                case '7':
                    break;
                case '8':
                    boolean goodEmployeeNumber = false;
                    boolean goodTrackingNumber = false;
                    boolean goodUserId = false;
                    boolean goodInput = false;

                    while(!goodEmployeeNumber) {
                        System.out.println("Please enter your Employee number: ");
                        String employeeNumber = in.nextLine();
                        if (shippingstore.findEmployee(Integer.parseInt(employeeNumber))) {
                            goodEmployeeNumber = true;
                            while(!goodTrackingNumber) {
                                System.out.println("To complete shipping transaction, please enter the Tracking Number of the package who's shipping transaction you would like to process.");
                                String trackingNumber = in.nextLine();
                                if (shippingstore.findPackageOrder(trackingNumber) != -1) {
                                    goodTrackingNumber = true;
                                    while (!goodUserId) {
                                        System.out.print("Please enter the ID of the user this shipping transaction will be assocaited with: ");
                                        String userId = in.nextLine();
                                        if (shippingstore.findUser(Integer.parseInt(userId))) {
                                            goodUserId = true;
                                            while (!goodInput) {
                                                System.out.println("Please enter the Shipping Date, Delivery Date, and cost of shipping in this format: \nSHIPPING DATE   DELIVERY DATE   COST OF SHIPPING \n example: 03/21/2017 03/24/2017 15");
                                                String transactionTemp = in.nextLine();
                                                String words[] = transactionTemp.split(" ");
                                                if (shippingstore.isDateValid(words[0]) && shippingstore.isDateValid(words[1])) {
                                                    shippingstore.addTransaction(employeeNumber, trackingNumber, userId, words[0], words[1], words[2]);
                                                    goodInput = true;
                                                    shippingstore.removeOrder(trackingNumber);
                                                } else {
                                                    System.out.println("Input Invalid.");
                                                }
                                            }
                                        } else {
                                            System.out.println("User ID entered does not match any current users in database.");
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("Tracking number entered is not associated with any packages currently in database.");
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Employee numbered entered not found.");
                            break;
                        }
                    }
                case '9':
                    shippingstore.showTransactionOrders();
                    break;
                case 'h':
                    System.out.println(welcomeMessage);
                    break;
                case 'l':
                    shippingstore.listAllUsers();
                    break;
                default:
                    System.out.println("That is not a recognized command. Please enter another command or 'h' to list the commands.");
                    break;

            }

            System.out.println("Please enter another command or 'h' to list the commands or 'l' to see all users.\n");
            selection = in.next().charAt(0);

            in.nextLine();
        }

        in.close();
        shippingstore.flush();
        
        System.out.println("Done!");

    }
}
