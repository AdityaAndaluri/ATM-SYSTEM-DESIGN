
# ATM Project

## Andaluri S P V M Aditya

This is the first version of the software which simulates the functionality of 
ATM machine.  
The software encapsulates the functionality of the hardware devices (e.g.,cash
dispenser, deposit slot) within software components ,but it will not concern itself with how
these devices perform their duties.
This version uses the computer’s monitor to simulate the ATM’s
screen,and the computer’s key-board to simulate the ATM’s keypad.  
  
    
Firstly, let's have a look at the functionality....




## Functionality:

- The console displays a welcome message and prompts the user to enter an account
  number.
- User's account will be validated when he enters his pin ,using the keypad.
- Then he gets access to Main Menu which has four Banking options:  
  - 1.BalanceEnquiry  
  - 2.pinchange   
  - 3.cashwithdrawl  
  - 4.cashdeposit.
- one can select the numbers from one to four to avail the option respectively.  
- Thereafter, UI asks the user to enter type of account he has:  
    - 1.Current account  
    - 2.Savings account
- one can select the one or two to validate his account type since a user can only have one type of account.  
- If he have choosen option 1, his current account balance will be shown.  
- If he have choosen option 2, he can change his pin only if he confirms his old pin onceagain.  
- If he have choosen option 3, console prompts the user to enter amount he wants to withdraw, amount will be deducted once the transaction is successful.  
- If he have choosen option 4, console prompts the user to enter amount he wants to deposit, amount will be added once the transaction is successful.


## Classes and Methods Used...  
- Account class
    - Attributes:
        - double balance;
        - String Name;
        - int accountnumber;
        - int pin;
        - int type;
        - int blockstatus;
    - Methods:
        - showbalance();
        - changebalance();
        - showName();
        - changeName();
        - showaccountnumber();
        - changeaccountnumber();
        - showpin();
        - changepin();
        - showtype();
        - changetype();
        - showblockstatus();
        - changeblockstatus();
- cash_dispenser class
    - Method:
        - giveDenomination();
- deposit_slot class
    - Method:
        - recieveDenomination();
- ATMmachine class
    - Main Method - all the code for the user interface lies here
- EXIT1 class
    - printmsg method - just prints the exit message when there is a wrong input giveDenomination
- EXIT2 class which extends EXIT1
    - printmsg method - just prints the exit message when successful transaction happened.

//Above method names suggest what they do.   
//The Zip file also has the accounts database text file which has all the details of accounts.  
//The code internally retrieve the accounts details from the text file and saves back the changes when a transaction is completed.
# Using OOPS concepts:
## Abstraction  
All the internal implementations and all the class attributes are made private so that the user cannot access them directly and he is not aware of the implementations.Rather user would be only allowed to give the seriel number for the funtion he wants to perform.  
## Interfaces  
A concrete class must implement all the abstract methods specified in the interface.  
All the interfaces accoun , cdps , dpsa are implemented to give the above mentioned classes.  
## Encapsulation
This code integrates variables and (methods) into a single unit.All the class's variables are hidden(made private) from other classes and can only be accessed by the methods(show and change methods) of the class in which they are found.  
## Polymorphism  
method overriding is used for the method in exit class. To print two differert msgs in two different situations , one in the case of successful transaction and another in case of wrong details.

# Constraints Satisfied
- allows any Denomination 
- limits user not to enter wrong pin more than 3 times, his account will be blocked otherwise, if the count of blockstatus reaches 3. He needs to go to the bank to rectify it(in the sense, need to manually change count to zero in the database txt file).
- covered maximum oops concepts
- pin length can be variables, provides more security.
- cardless transactions
