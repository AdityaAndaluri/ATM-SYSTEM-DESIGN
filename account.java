interface accoun{
    public double showbalance();
    public void showName();
    public void showaccountnumber();
    public int showpin();
    public void changebalance(double bal);
    public void changeName(String s);
    public void changeaccountnumber(int s);
    public void changepin(int x);
    
}                                                               //interface to build account class
interface cdps{
    public void giveDenomination(account a,double money);
}                                                               //interface for cash dispenser 
interface dpsa{
    public void recieveDenomination(account a,double money);
}                                                               //interface for depositslot
class cash_dispenser implements cdps{
        public void giveDenomination(account a,double money){
            if(money <= a.showbalance()){
            System.out.println("Amount withdrawn :"+money);
            a.changebalance(a.showbalance()-money);}
            else{
                System.out.println("Your Balance is not sufficient");
            }
        }
    }
class deposit_slot implements dpsa{
        public void recieveDenomination(account a,double money){
            System.out.println("Recieved Denomination :"+money);
            a.changebalance(a.showbalance()+money);
        }
    }
class EXIT1{
   public void printmsg(){
    System.out.println("ThankYou!!...VisitAgain");
   }
}
class EXIT2 extends EXIT1{
    public void printmsg(){
        System.out.println("Please ensure that your details are correct!!");
    }
}
public class account implements accoun{
        private double balance;
        private String Name;
        private int accountnumber;
        private int pin;
        private int type;
        private int blockstatus;
        account(double b,String n,int acn,int p,int type,int blockstatus){
            this.balance=b;
            this.Name=n;
            this.accountnumber=acn;
            this.pin=p;
            this.type = type;
            this.blockstatus = blockstatus;
        }
        public double showbalance(){
            return this.balance;
        }
        public void showName(){
            System.out.println(this.Name);
        }
        public void showaccountnumber(){
            System.out.println(this.accountnumber);
        }
        public int showpin(){
            return this.pin;
        }
        public int showtype(){
            return this.type;
        }
        public int showblockstatus(){
            return this.blockstatus;
        }
        public void changebalance(double bal){
            this.balance=bal;
        }
        public void changeName(String s){
            System.out.println("Accountant Name changed Successfully");
            this.Name=s;
        }
        public void changeaccountnumber(int s){
            System.out.println("Account number changed Successfully");
            this.accountnumber=s;
        }
        public void changepin(int x){
            System.out.println("Pin changed Successfully");
            this.pin=x;
        }
        public void changetype(int x){
            System.out.println("Account type changed Successfully");
            this.type=x;
        }
        public void changeblockstatus(int x){
            this.blockstatus=x;
        }

        public static void main(String[] args) {
            
        }
}

