// Daniel Busuttil, 4th Feb

/* Customer object to represent the customers (and their data) that the
 * bank has. Has accesssors for 'name' and 'discountPercentage'. */

// Necessary libaries:
import java.util.HashMap;
import java.lang.Long;

// Custom exception in case an invalid name/String is passed to the constructor
class InvalidNameException extends Exception {
}

// Custom exception in case an invalid discount percentage rate is passed to the constructor
class InvalidDiscountException extends Exception {
}
  
public class Customer extends ValidCheckLibrary {
  
  // Private variables to hold customer-specific data
  private final String name;
  // Unique long ID to identify the customer
  private final long ID;
  /* Long IDs/counters to serve as an identifiers for a Customer's accounts (of each type), will increment
   * each time an account of their type is successfully created. */
  private long checkAccID = 0;
  private long saveAccID = 0;
  // Format is 0.34 (<--> 34%) to allow for simpler computation later
  private final double discountPercentage;
  /* While ArrayLists are more efficient they only take int indices, so I'm using a HashMap as my data structure of
   * choice for CheckingAccounts and SavingsAccounts. I've chosen to make two seperate HashMaps because it would
   * allow for the HashMap class to sort the keys and for simplicity/usability (as they'll only have 1
   * 'return type'), that way to retrieve the value associated with a specific key is O(log n). */
  private static final HashMap<Long, CheckingAccount> checkAccHMap = new HashMap<Long, CheckingAccount>();
  private static final HashMap<Long, SavingsAccount> saveAccHMap = new HashMap<Long, SavingsAccount>();

  
  /* Constructor for Customer. I'm assuming that: names don't have to be formatted as: 'FIRST NAME, LAST
   * NAME' i.e. any non-zero string will serve as a valid 'name', any non-zero ID number is valid and
   * any non-negative double is a valid 'inDiscPercentage'. Even though, in practice, an invalid ID
   * won't be passed, I've decided to still implement validity checks for it; I realize that this
   * prevents some security concerns while opening up avenues for other attacks but I feel this is the
   * best way of going about it. */
  public Customer( String inName, long inID, double inDiscPercentage ) throws InvalidIDException, InvalidNameException, InvalidDiscountException {
    // Series of input guards with the appropriate Exception being thrown if the guard is failed
    if ( inName.length() == 0 ) {
      throw new InvalidNameException();
    }
    else if ( inID < 0 ) {
        throw new InvalidIDException();
    }
    else if ( inDiscPercentage < 0.0 ) {
      throw new InvalidDiscountException();
    }
    
    // Guards passed
    this.name = inName;
    this.ID = inID;
    this.discountPercentage = inDiscPercentage;
    
  }
  
  /* Method to create a CheckingAccount under the customer ID that is passed as input. AccountID &
   * accountType are handled internally and don't depend on user input. */
  public boolean createCheckAccount( double initBalance, long custID ) throws InvalidBalanceException, InvalidIDException {
    try {
      checkAccHMap.put( new Long(this.checkAccID), new CheckingAccount(initBalance, custID) );
      this.checkAccID++;
      return true;
    }
    catch ( InvalidBalanceException e ) {
      System.out.println("You've entered an invalid initial balance, please try again!");
    }
    catch ( InvalidIDException e ) {
      System.out.println("You've entered an invalid customer ID, please try again!");
    }
    
    // Only reached if guards were triggered
    return false;
  }
  
  /* Method to create a SavingsAccount under the customer ID that is passed as input. AccountID &
   * accountType are handled internally and don't depend on user input. */
  public boolean createSaveAccount( double initBalance, long custID ) throws InvalidBalanceException, InvalidIDException {
    try {
      saveAccHMap.put( new Long(this.saveAccID), new SavingsAccount(initBalance, custID) );
      this.saveAccID++;
      return true;
    }
    catch ( InvalidBalanceException e ) {
      System.out.println("You've entered an invalid initial balance, please try again!");
    }
    catch ( InvalidIDException e ) {
      System.out.println("You've entered an invalid customer ID, please try again!");
    }
    
    // Only reached if guards were triggered
    return false;
  }
  
  /* Public method to access one of the HashMaps (without making the data structure itself public) and perform
   * a desired action: 'b' -> return balance, 'd' -> deposit, 'w' -> withdraw, 't' -> transfer. If action != 'b'
   * accessAccount will enter a while loop to parse user input to get the deposit/withdrawl/transfer amount (and
   * the transfer account's ID if action == 't'). If any of the parameters are invalid, the method returns false,
   * else it returns true. */
  public double accessAccount( long accNumber, char action, char accType ) {
    // Guard against negative account number input or a number greater than the number of existing accounts
    if ( accNumber < 0  || 
       ( accNumber > this.checkAccID && accType == 'c' ) ||
       ( accNumber > this.saveAccID && accType == 's' ) ) {
      System.out.println("The account number/ID you entered was invalid, please try again!");
      return -1;
    }
    // Guard against invalid char input for 'action'
    else if ( action != 'b' && action != 'd' &&
              action != 'w' && action != 't' ) {
      System.out.print("The action you entered was invalid. ");
      System.out.print("Please enter: 'b' (if you wish to view your balance), 'd' (if you wish to make a deposit), ");
      System.out.println("'w' (if you'd like to make a withdrawal) or 't' (if you'd like to transfer between accounts)!");
      return -2;
    }
    // Guard against an invalid input for 'accType'
    else if ( accType != 'c' && accType != 's' ) {
      System.out.print("The account type you entered was invalid. ");
      System.out.println("Please enter either 's' or 'c' for a savings or checking account respectively!");
      return -3;
    }
    
    // If user wants to getBalance()
    if ( action == 'b' ) {
      if ( accType == 'c' ) return checkAccHMap.get( accNumber ).getBalance();
      else return saveAccHMap.get( accNumber ).getBalance();
    }
    
    // The other three require a 'double amount' so we do it outside of the switch/case statement
    System.out.print("Please enter the amount you'd like to access: ");
    double amount = isValidDoubleInput();    
    
    /* Switch/case to handle action input. Since we've made sure all input is valid, we don't check
     * inside the switch/case. */
    switch( action ) {
      // User wants to deposit money
      case 'd':
        if ( accType == 'c' ) {
          if ( checkAccHMap.get( accNumber ).deposit( amount ) )
            return 0;
          else return -4;
        }
        else {
          if ( saveAccHMap.get( accNumber ).deposit( amount, this.discountPercentage ) )
            return 0;
          else return -4;
        }
        
      // User wants to withdraw money
      case 'w' :
        if ( accType == 'c' ) {
          if ( checkAccHMap.get( accNumber ).withdraw( amount, this.discountPercentage ) )
            return 0;
          else return -5;
        }
        else {
          if ( saveAccHMap.get( accNumber ).withdraw( amount ) )
            return 0;
          else return -5;
        }
      
      // User wants to transfer money between accounts
      case 't' :
        System.out.print("Please enter the ID of the account you'd like to transfer money into: ");
        Long destID = isValidLongInput();
        System.out.print("Please enter the type of the account you'd like to transfer money into: ");
        char destType = isValidAccType();
        // If both are checking
        if ( accType == 'c' && destType == 'c' ) {
          if ( !checkAccHMap.get( accNumber ).transferOut( amount ) ) {
            System.out.println("That account doesn't have the funds to transfer as you requested, please try again!");
            return -6;
          }
          checkAccHMap.get( destID ).transferIn( amount );
          return 0;
        }
        // If both are savings
        else if ( accType == 's' && destType == 's' ) {
          if ( !saveAccHMap.get( accNumber ).transferOut( amount ) ) {
            System.out.println("That account doesn't have the funds to transfer as you requested, please try again!");
            return -6;
          }
          saveAccHMap.get( destID ).transferIn( amount );
          return 0;
        }
        // The two mix-cases:
        else if ( accType == 'c' && destType == 's' ) {
          if ( !checkAccHMap.get( accNumber ).transferOut( amount ) ) {
            System.out.println("That account doesn't have the funds to transfer as you requested, please try again!");
            return -6;
          }
          saveAccHMap.get( destID ).transferIn( amount );
          return 0;
        }
        else {
          if ( !saveAccHMap.get( accNumber ).transferOut( amount ) ) {
            System.out.println("That account doesn't have the funds to transfer as you requested, please try again!");
            return -6;
          }
          checkAccHMap.get( destID ).transferIn( amount );
          return 0;
        }
        // We know that if this is being invoked then amount > 0, so we don't check in the (immediate) above
    }
    
    return 0;
  }
  
  
  
  
}