// Daniel Busuttil, 4th Feb

/* Subclass of BankAccount. Will handle the withdraw and deposit methods and
 * subclass constructor. */

// Necessary library
import java.lang.Exception;

public class SavingsAccount extends BankAccount {
  // Constructor that invokes the super-class' constructor
  public SavingsAccount( double initAmount, long customerID ) throws InvalidBalanceException, InvalidIDException {
    super(initAmount, customerID);
  }
  
  /* Method to deposit money into balance. Returns false if any amount is negative,
   * true otherwise. */
  public boolean deposit( double amount, double discount ) {
    if ( amount < 0.0 ) return false;
    
    /* If guards are passed we increase balance by 'amount' and award $1 + the
     * discount percentage to the customer. */
    this.balance = this.balance + amount + ( 1 + discount );
    return true;
  }
  
  /* Method to withdraw money from 'balance' assuming 'amount' > 1000 and the customer
   * is good for it. */
  public boolean withdraw( double amount ) {
    // Guard against invalid inputs
    if ( amount < 1000.0 ||
         this.balance - amount < 0.0 )
      return false;
    
    this.balance = this.balance - amount;
    return true;
  }
  
}