// Daniel Busuttil, 4th Feb

/* Java file whose main method will implement the text-based menu. Bank will also handle the
 * creation and management of the Customer data structure (hashmap indexed with longs), monitoring
 *  and incrementing of the (current) customer ID (to ensure uniqueness). */

// Necessary libaries:
import java.util.HashMap;
import java.util.Scanner;
import java.lang.Long;

public class Bank extends ValidCheckLibrary {
  // Long to store the next available ID
  private static long curCustomerID;
  /* While ArrayLists would be more efficient here, because we have to try to make our application
   * hold 'infinite' (bounded by RAM) # of accounts I've chosen to use HashMaps (with Long indices)
   * as the data structure for Customer. */
  private static final HashMap<Long, Customer> customerHMap = new HashMap<Long, Customer>();
  
  
  /* Method to create a Customer object & add it to our data struct, indexed by a long. If the creation is
   * successful, then the counter is incremented. */
  public static boolean createCustomer( String inName, double inDiscPercentage ) throws InvalidNameException, InvalidIDException, InvalidDiscountException {
    // Adds a customer to customerAList, if the Customer constructor doesn't throw an exception
    try {
      customerHMap.put( new Long(curCustomerID), new Customer(inName, curCustomerID, inDiscPercentage) );
      curCustomerID++;
      return true;
    }
    
    // If any of our guards are triggered we print an error message and return false
    catch ( InvalidNameException e ) {
      System.out.println("You've entered an invalid customer name, please try again!");
    }
    catch ( InvalidIDException e ) {
      System.out.println("You've entered an invalid customer ID, please try again!");
    }
    catch ( InvalidDiscountException e ) {
      System.out.println("You've entered an invalid discount percentage, please try again!");
    }
    return false;
  }
  
  
  // Main method, handles the menu loop
  public static void main(String[] args)
    // Possible exceptions thrown but are all handled in their own methods
    throws InvalidNameException, InvalidIDException, InvalidDiscountException, InvalidBalanceException, InvalidIDException {
    // We could break out of the loop with a break state, but this improves readiblity
    boolean trueLoop = true;
    // Char to hold the user's choice (in the menu)
    char choice = '.';
    
    while ( trueLoop ) {
      choice = isValidChoice();
      // I use if-statements not swtich/case to have different scopes
      if ( choice == 'a' ){
        System.out.print("Please enter the customer's name: ");
        String inName = isValidName();
        System.out.print("Please enter their discount rate: ");
        double inPercent = isValidPercentage();
        createCustomer(inName, inPercent);
        System.out.println("Customer created!\n");
        continue;
      }
      else if ( choice == 'b' ) {
        System.out.print("Please enter your customer ID: ");
        Long custID = new Long(s_isValidLongInput());
        System.out.print("Please enter the type of account you'd like to create (s -> savings, c -> checkings): ");
        char accType = s_isValidAccType();
        System.out.print("Please enter the balance you'd like to begin with: ");
        double inBalance = s_isValidDoubleInput();
        if ( accType == 'c' ) {
          customerHMap.get( custID ).createCheckAccount(inBalance, custID); 
        }
        else {
          customerHMap.get( custID ).createSaveAccount(inBalance, custID); 
        }
        System.out.println("Account created!\n");
        continue;
      }
      else if ( choice == 'g' ) {
        trueLoop = false;
        continue;
      }
      
      // All the following operations (besides quitting) require the following:
      System.out.print("Please enter your customer ID: ");
      Long custID = new Long(s_isValidLongInput());
      System.out.print("Please enter the type of account you'd like to access (s -> savings, c -> checkings): ");
      char accType = s_isValidAccType();
      System.out.print("Please enter your account ID: ");
      Long accID = new Long(s_isValidLongInput());
      
      if ( choice == 'c' ) {
        System.out.print("Here is the balance for account #" + accID + ": $");
        System.out.println( customerHMap.get( custID ).accessAccount( accID, 'b', accType) );
      }
      else if ( choice == 'd' ) {
        if ( customerHMap.get( custID ).accessAccount( accID, 'd', accType) == 0 )
          System.out.println("Your deposit was successful!");
        else
          System.out.println("Your deposit was not successful, please try again!");
      }
      else if ( choice == 'e' ) {
        if ( customerHMap.get( custID ).accessAccount( accID, 'w', accType) == 0 )
          System.out.println("Your withdraw was successful!");
        else
          System.out.println("Your withdraw was not successful, please try again!");
      }
      // By process of elimination we can avoid another else if
      else {
        if ( customerHMap.get( custID ).accessAccount( accID, 't', accType) == 0 )
          System.out.println("Your transfer was successful!");
      }
    }
    System.out.println("Thank you for using this application, have a nice day!");
  }
}