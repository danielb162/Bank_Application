// Daniel Busuttil, 4th Feb

// Mini-library to hold our isValid checks for a variety of types

// Necessary libraries:
import java.util.Scanner;
import java.lang.Long;

public class ValidCheckLibrary {
 
  // Non-static isValid checks:
  
  // Checks if user input is a valid, non-negative double
  public double isValidDoubleInput(){
    Scanner userInput = new Scanner(System.in);
    String rawInput = "";
    double result = -1;
    
    // Guards against inproper input
    while ( true ) {
      rawInput = userInput.nextLine();
      try {
        result = Double.parseDouble( rawInput );
        if ( result < 0 ) {
          System.out.println("That's not a valid balance, please enter a non-negative, floating-point amount!");
          continue;
        }
      }
      catch ( NumberFormatException e ) {
        System.out.println("That's not a numerical input, please try again!");
        continue;
      }
      // Only reached if guards weren't triggered
      break;
    }
    
    userInput.close();
    return result;
  }
  
  // Checks if user input is a valid, non-negative Long
  public Long isValidLongInput(){
    Scanner userInput = new Scanner(System.in);
    String rawInput = "";
    Long result = new Long(-1);
    
    // Guards against inproper input
    while ( true ) {
      rawInput = userInput.nextLine();
      try {
        result = Long.parseLong( rawInput );
        if ( result < 0 ) {
          System.out.println("That's not a valid balance, please enter a non-negative, floating-point amount!");
          continue;
        }
      }
      catch ( NumberFormatException e ) {
        System.out.println("That's not a numerical input, please try again!");
        continue;
      }
      // Only reached if guards weren't triggered
      break;
    }
    
    userInput.close();
    return result;
  }
  
  // Checks if user input is a valid char (to represent an account type)
  public char isValidAccType(){
    Scanner userInput = new Scanner(System.in);
    String rawInput = "";
    
    // Guards against inproper input
    while ( true ) {
      rawInput = userInput.nextLine();
      
      // Checking that we have a single character and that its valid
      if ( rawInput.length() == 0 || rawInput.length() > 1 || 
         ( rawInput.charAt(0) != 's' && rawInput.charAt(0) != 'c' ) ) {
        System.out.println("That's not a valid (single) character for this operation, please try again!");
        continue;
      }
      
      // Only reached if guards weren't triggered
      break;
    }
    
    userInput.close();
    return rawInput.charAt(0);
  }

  // Static isValid checks (same name but with a 's_' prefix to distinguish):
  
    // Checks if user input is a valid, non-negative double
  public static double s_isValidDoubleInput(){
    Scanner userInput = new Scanner(System.in);
    String rawInput = "";
    double result = -1;
    
    // Guards against inproper input
    while ( true ) {
      rawInput = userInput.nextLine();
      try {
        result = Double.parseDouble( rawInput );
        if ( result < 0 ) {
          System.out.println("That's not a valid balance, please enter a non-negative, floating-point amount!");
          continue;
        }
      }
      catch ( NumberFormatException e ) {
        System.out.println("That's not a numerical input, please try again!");
        continue;
      }
      // Only reached if guards weren't triggered
      break;
    }
    
    userInput.close();
    return result;
  }
  
  // Checks if user input is a valid, non-negative Long
  public static Long s_isValidLongInput(){
    Scanner userInput = new Scanner(System.in);
    String rawInput = "";
    Long result = new Long(-1);
    
    // Guards against inproper input
    while ( true ) {
      rawInput = userInput.nextLine();
      try {
        result = Long.parseLong( rawInput );
        if ( result < 0 ) {
          System.out.println("That's not a valid balance, please enter a non-negative, floating-point amount!");
          continue;
        }
      }
      catch ( NumberFormatException e ) {
        System.out.println("That's not a numerical input, please try again!");
        continue;
      }
      // Only reached if guards weren't triggered
      break;
    }
    
    userInput.close();
    return result;
  }
  
  // Checks if user input is a valid char (to represent an account type)
  public static char s_isValidAccType(){
    Scanner userInput = new Scanner(System.in);
    String rawInput = "";
    
    // Guards against inproper input
    while ( true ) {
      rawInput = userInput.nextLine();
      
      // Checking that we have a single character and that its valid
      if ( rawInput.length() == 0 || rawInput.length() > 1 || 
         ( rawInput.charAt(0) != 's' && rawInput.charAt(0) != 'c' ) ) {
        System.out.println("That's not a valid (single) character for this operation, please try again!");
        continue;
      }
      
      // Only reached if guards weren't triggered
      break;
    }
    
    userInput.close();
    return rawInput.charAt(0);
  }
  
  // Checks if user input is a valid char (to represent a menu choice)
  public static char isValidChoice(){
    System.out.println("Menu options:");
    System.out.print("(a) create customer, (b) create bank account, (c) Get balance, ");
    System.out.println("(d) Deposit, (e) Withdrawal, (f) Make a transfer, (g) Quit");
    Scanner userInput = new Scanner(System.in);
    String rawInput = "";
    
    // Guards against inproper input
    while ( true ) {
      rawInput = userInput.nextLine();
      
      // Checking that we have a single character and that its valid
      if ( rawInput.length() == 0 || rawInput.length() > 1 || 
         ( rawInput.charAt(0) != 'a' && rawInput.charAt(0) != 'b' && rawInput.charAt(0) != 'c' &&
           rawInput.charAt(0) != 'd' && rawInput.charAt(0) != 'e' && rawInput.charAt(0) != 'f' && 
           rawInput.charAt(0) != 'g' ) ) {
        System.out.println("That's not a valid (single) character for this operation, please try again!");
        continue;
      }
      
      // Only reached if guards weren't triggered
      break;
    }
    
    userInput.close();
    return rawInput.charAt(0);
  }
  
  public static char isValidAction(){
    System.out.println("Possible actions:");
    System.out.print("'b' -> See your current balance, 'd' -> make a deposit into your account, ");
    System.out.println("'w' -> make a withdrawal from your account, 't' -> Transfer money between accounts");
    Scanner userInput = new Scanner(System.in);
    String rawInput = "";
    
    // Guards against inproper input
    while ( true ) {
      rawInput = userInput.nextLine();
      
      // Checking that we have a single character and that its valid
      if ( rawInput.length() == 0 || rawInput.length() > 1 || 
         ( rawInput.charAt(0) != 'b' && rawInput.charAt(0) != 'd' &&
           rawInput.charAt(0) != 'w' && rawInput.charAt(0) != 't' ) ) {
        System.out.println("That's not a valid (single) character for this operation, please try again!");
        continue;
      }
      
      // Only reached if guards weren't triggered
      break;
    }
    
    userInput.close();
    return rawInput.charAt(0);
  }
  
  // Checks if user input is a valid name
  public static String isValidName(){
    Scanner userInput = new Scanner(System.in);
    String input = "";
    
    // Guards against inproper input
    while ( true ) {
      input = userInput.nextLine();
      
      // Checking that we have a non-zero string, only condition for validity
      if ( input.length() == 0 ) {
        System.out.println("That's not a valid name, please try again!");
        continue;
      }
      
      // Only reached if guards weren't triggered
      break;
    }
    
    userInput.close();
    return input;
  }
  
  public static double isValidPercentage(){
    Scanner userInput = new Scanner(System.in);
    String rawInput = "";
    double result = -1;
    
    // Guards against inproper input
    while ( true ) {
      rawInput = userInput.nextLine();
      try {
        result = Double.parseDouble( rawInput );
        if ( result < 0.0 || result > 1.0 ) {
          System.out.println("That's not a valid discount rate, please enter a floating-point amount between 0.0 and 1.0!");
          continue;
        }
      }
      catch ( NumberFormatException e ) {
        System.out.println("That's not a numerical input, please try again!");
        continue;
      }
      // Only reached if guards weren't triggered
      break;
    }
    
    userInput.close();
    return result;
  }
}