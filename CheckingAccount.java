// Daniel Busuttil, 4th Feb

/* Subclass of BankAccount. Will handle the withdraw method and subclass 
 * constructor. */

// Necessary library
import java.lang.Exception;

public class CheckingAccount extends BankAccount {
  // Constructor that invokes the super-class' constructor
  public CheckingAccount( double initAmount, long customerID ) throws InvalidBalanceException, InvalidIDException {
    super(initAmount, customerID);
  }
  
  /* Method to deduct 'amount' from this.balance if guards are passed. Will
   * return false if any guards are triggered; true otherwise. */
  public boolean withdraw( double amount, double discount ) {
    // Charge of $1 modified by the discount rate
    double penalty = ( 1.0 + discount );
    
    // Guards before we deduct from balance
    if ( amount < 0.0 ||
         this.balance - amount - penalty < 0.0 ) {
      return false;
    }
    // If guards are passed we deduct from balance appropriately
      this.balance = this.balance - amount - penalty;
      return true;
  }
  
}