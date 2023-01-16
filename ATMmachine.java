import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileWriter; 
import java.io.IOException; 
public class ATMmachine{
    public static void main(String [] args){
        deposit_slot dps= new deposit_slot();                      // depostit slot creation with dps object
        cash_dispenser cd=new cash_dispenser();                    // cash dispenser creation with cs object
        EXIT2 ex =new EXIT2();                                     // creation of two exit objects
        EXIT1 ex1 =new EXIT1();
        while(1>0){
            Vector<Integer> acnumbers = new Vector<Integer>();     //using vectors to store all the details retrieved from database
            Vector<Integer> acpins = new Vector<Integer>();
            Vector<String> acnames = new Vector<String>();
            Vector<Double> acbalance = new Vector<Double>();
            Vector<Integer> actypes = new Vector<Integer>();
            Vector<Integer> acstatus = new Vector<Integer>();
                                                                    // Starts reading the database file
                try {                                              
                    File Obj = new File("accountdatabase");
                    Scanner Reader = new Scanner(Obj);
                    while (Reader.hasNext()) {
                        int acno = Reader.nextInt();
                        acnumbers.add(acno);
                        int acpin = Reader.nextInt();
                        acpins.add(acpin);
                        String name = Reader.next();
                        acnames.add(name);
                        double bal = Reader.nextDouble();
                        acbalance.add(bal);
                        int actype = Reader.nextInt();
                        actypes.add(actype);
                        int status = Reader.nextInt();
                        acstatus.add(status);
                    }
                    Reader.close();
                }
                catch (FileNotFoundException e) {
                    System.out.println("An error has occurred.");
                    e.printStackTrace();
                }


                System.out.print("\033[H\033[2J");          /* these two statements are exclusively for clearing the curren screen */
                System.out.flush();


//welcome message
        System.out.println("                        NAMASKAR!!!");
        Scanner q=new Scanner(System.in);
        System.out.println("                  WELCOME TO ADITYA BANK");
        System.out.println("ENCOURAGE CARDLESS TRANSACTIONS TO AVOID SPREADING OF DISEASES");
        System.out.println("Enter Your 5 digit Ac Number : ");
        int acnum=q.nextInt();
//prompts for ac number and checks the existence of acc number
        int index=acnumbers.indexOf(acnum);
        if(index==-1){
            System.out.println("Your Account doesn't exist!");
            ex.printmsg();
            System.out.println("Exit?(y or n):");
            String tem=q.next();
            continue;
        }  

//account object creation using the details of entered acc number in vectors
        account a = new account(acbalance.elementAt(index),acnames.elementAt(index),acnumbers.elementAt(index),acpins.elementAt(index),actypes.elementAt(index),acstatus.elementAt(index));

//checking blockstatus
        if(a.showblockstatus()==3){
            System.out.println("!!You have already entered 3 incorrect pins!!");
            System.out.println("Your Debit Card has been blocked");
            System.out.println("Please go to the database and renew your block_status to 0");
            System.out.println("Satisfied with our service?:(y or n)");
            String s=q.next();
            continue;

        }

//prompts for pin and validation
        System.out.println("Enter Your 5 digit Pin :");
        int pins = q.nextInt();
        if(pins!=a.showpin()){
            System.out.println("You entered Wrong Password");
            ex.printmsg();
//if wrong pin entered then increases the blockstatus count and exits.
            a.changeblockstatus(a.showblockstatus()+1);
            acstatus.set(index,a.showblockstatus());
            System.out.println("Exit?(y or n)");
            String str1 = q.next();
//writes the updates to database file
            try{
                int i=0;
                FileWriter Writer
                    = new FileWriter("accountdatabase");
                while(i<acnames.size()){
                    Writer.write(acnumbers.elementAt(i)+" ");
                    Writer.write(acpins.elementAt(i)+" ");
                    Writer.write(acnames.elementAt(i)+" ");
                    Writer.write(acbalance.elementAt(i)+" ");
                    Writer.write(actypes.elementAt(i)+" ");
                    Writer.write(acstatus.elementAt(i)+"\n");
                    i++;
                }
                Writer.close();
            }
            catch(IOException e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
            continue;
        }
        System.out.print("\033[H\033[2J");  
        System.out.flush();

//prompts for main menu choosing
        System.out.println("                          MAIN MENU");
        System.out.println("1.BALANCE ENQUIRY                                 2.PIN CHANGE");
        System.out.println("3.CASH WITHDRAWAL                                 4.CASH DEPOSIT");
        System.out.print("CHOOSE ANY OPTION:");
        int option = q.nextInt();
        System.out.print("\033[H\033[2J");  
        System.out.flush();
//prompts to choose account type and validation of account type
        System.out.println("                          Account Type?");
        System.out.println("1.CURRENT                                         2.SAVINGS");
        int typeoption= q.nextInt();
        if(a.showtype()!=typeoption){
            System.out.println("Account Type not Matching!");
            System.out.println("Exit?(y or n)");
            ex.printmsg();
            String str99 = q.next();
            continue;
        }

//after validation....

        if(option == 1 ){
           System.out.println("Your Current Balance : "+a.showbalance()+" rupees");    // call showbalance method 
        }
        else if(option == 2){
            System.out.println("Enter your current pin : ");
            int cpin = q.nextInt();
            if(cpin!=a.showpin()){
                System.out.println("You entered Wrong Pin:");
                ex.printmsg();
                System.out.println("Exit?(y or n)");
                String str3 = q.next();
                continue;
            }
            System.out.println("Enter new pin : ");
            int npin = q.nextInt();
            System.out.println("Confirm your new pin : ");
            int conpin =q.nextInt();
            if(npin!=conpin){
                System.out.println("Both pins doesn't match");
                ex.printmsg();
                System.out.println("Exit?(y or n)");
                String str6 = q.next();
                continue;
            }
            a.changepin(npin);
        }
        else if(option == 3){
                System.out.println("Enter the amount : ");
                double amo = q.nextDouble();
                cd.giveDenomination(a, amo);                            // calls givedenomination method
        }
        else if(option == 4){
            System.out.println("Enter the amount : ");
            double ami = q.nextDouble();
            dps.recieveDenomination(a, ami);                           // calls recievedenomination method
        }
        else{
            System.out.println("Enter valid numbers(from 1 to 4)");
        }
        acpins.set(index,a.showpin());
        acbalance.set(index, a.showbalance());

//writes the updates to txt file
        try{
            int i=0;
            FileWriter Writer
                = new FileWriter("accountdatabase");
            while(i<acnames.size()){
                Writer.write(acnumbers.elementAt(i)+" ");
                Writer.write(acpins.elementAt(i)+" ");
                Writer.write(acnames.elementAt(i)+" ");
                Writer.write(acbalance.elementAt(i)+" ");
                Writer.write(actypes.elementAt(i)+" ");
                Writer.write(acstatus.elementAt(i)+"\n");
                i++;
            }
            Writer.close();
        }
        catch(IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
      ex1.printmsg();
      System.out.println("Satisfied with our service?:(y or n)");
      String s=q.next();
      System.out.print("\033[H\033[2J");  
      System.out.flush(); 
     }
    }
}
