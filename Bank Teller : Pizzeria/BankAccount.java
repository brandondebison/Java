public class BankAccount {

    protected String accountName; 
    protected int accountNumber;
    protected double accountTransaction;
    protected double accountBalance;

    public String getAccountName(){
        return accountName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountName(String temp) {
        this.accountName = temp;
    }

    public void setAccountNumber(int temp) {
        this.accountNumber = temp;
    }  

    public void setAccountTransaction(double temp){
        this.accountTransaction = temp;
        this.accountBalance = this.accountBalance + this.accountTransaction;
        
    }

    public void setAccountBalance(double temp) {
        this.accountBalance = temp;
    }

    public BankAccount() {
        accountName = "new customer"; 
        accountNumber = 0;
        accountBalance = 0.00;
    }

    public BankAccount(String s1, int d1, double d2) {        

        this.accountName = s1;
        this.accountNumber = d1;
        this.accountTransaction = d2;
        this.accountBalance = this.accountBalance + this.accountTransaction;
    }

    public void Display() {
        System.out.println("Name on Account is: "+accountName);
        System.out.println("Account number is "+accountNumber);
        System.out.println("Account balance is $"+accountBalance);
        System.out.println("\n");
    
    }



}