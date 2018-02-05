// Daniel Busuttil, 4th Feb

/* Super-class (of CheckingAccount & SavingsAccount). Houses the: ID of its owner, account
 * balance & the getBalance and moneyTransfer methods (along with the constructor of
 * course). The withdraw method will be handled in BankAccount's subclasses to better
 * account for their idiosyncrasies. I'm choosing to use exceptions for constructor error
 * handling as constuctors can't have a return type (thus preventing returning 'simple'
 * ints as error codes). Has accessors for 'balance' and 'ownerID'. */

// Necessary library
import java.lang.Exception;

// Custom exception in case an invalid balance is passed to the constructor
class InvalidBalanceException extends Exception {
}
// Custom exception in case an invalid ID is passed to the constructor
class InvalidIDException extends Exception {
}


class BankAccount {
  /* Double to represent the account balance; made protected (vs private) because it needs
   * to be modified in BankAccounts' sub-classes. */
  protected double balance;
  // Long to represent the ID of the account owner
  private final long ownerID;
  
  
  /* Constructor to initialize balance to the specified amount, throws InvalidBalanceException
   * & InvalidIDException to handle invalid input (I'm assuming that a negative balance is invalid). */
  public BankAccount( double initAmount, long custID ) throws InvalidBalanceException, InvalidIDException {
    // Guard to initialize balance
    if ( initAmount < 0.0 ) {
      throw new InvalidBalanceException();
    }
    else if ( custID < 0 ) {
      throw new InvalidIDException();
    }
    
    // Guards passed:
    this.balance = initAmount;
    this.ownerID = custID;
  }
  
  // Accessor to retrieve the BankAccount's balance 
  public double getBalance() {
    return this.balance;
  }
  
  
  /* Method to deposit money into BankAccount.balance. Returns false if any amount is negative,
   * true otherwise. Will be overloaded in the SavingsAccount subclass. */
  public boolean deposit( double amount ) {
    if ( amount < 0.0 ) return false;
    
    // If guards are passed we increase balance by 'amount'
    this.balance = this.balance + amount;
    return true;
  }
   
  /* These methods are made to, in combination, facilitate a transfer of funds between accounts. This is done
   * so that we don't need to use return references to the two accounts to be able to transfer. */
  public boolean transferIn( double amount ) {
    if ( amount < 0.0 ) {
      return false;
    }
    // If guards are passed we add to balance appropriately
    this.balance = this.balance + amount;
    return true;
  }
  
  public boolean transferOut( double amount ) {
    if ( amount < 0.0 ||
         this.balance - amount < 0.0 ) {
      return false;
    }
    // If guards are passed we deduct from balance appropriately
    this.balance = this.balance - amount;
    return true;
  }
  
  /* Note because withdraw is idiosyncratic to each subclass of BankAccount they'll each have
   * their own withdraw() method. */
   
}