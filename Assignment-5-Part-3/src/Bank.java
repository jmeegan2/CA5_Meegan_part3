/*
Saving and Current account. The balance amount in the bank for each account is based  on the following calculation :

Saving :
Balance = current balance + (savRate * current balance)

Current :
Balance = current balance + (curRate * current balance)

If the customer has a fixed deposit with the bank, then the bank will charge 150 for the service fee. The amount will be deducted automatically yearly.

a. Write a method of calcBalance()  for both subclasses.
b. Search a customer based on the account number entered then  display  detail  information of him/her. If the customer account number is not found, display an appropriate message.
c)	Count how many customers have the current account with the bank and the total
balance.
 */

// Class that holds the details of super class Bank
import java.util.Scanner;
public abstract class Bank {
    //instance variables
    private String accNo;
    private String custName;
    private int custGender;
    private String custJob;
    private double curBal;

    //parameterized constructor
    public Bank(String no,String name,int gender,String job,double bal)
    {
        accNo=no;
        custName=name;
        custGender=gender;
        custJob=job;
        curBal=bal;
    }

    //get and set methods
    //method returns the account number of customer
    public String getAccNo() {
        return accNo;
    }

    //method returns the name of customer
    public String getCustName()
    {
        return custName;
    }

    //method returns the customer gender in the string form
    public String getCustGender()
    {
        if(custGender==1)
            return "Male";
        else
            return "Female";
    }

    //method returns the job position of customer
    public String getCustJob() {
        return custJob;
    }

    //method returns the current balance of account
    public double getCurBal() {
        return curBal;
    }

    //method sets the account number with received parameter accNo
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    //method sets the customer name with received parameter custName
    public void setCustName(String custName) {
        this.custName = custName;
    }

    //method sets the customer gender with received parameter custGender
    public void setCustGender(int custGender) {
        this.custGender = custGender;
    }

    //method sets the customer job with received parameter custJob
    public void setCustJob(String custJob) {
        this.custJob = custJob;
    }

    //method sets the current balance of account with received parameter curBal
    public void setCurBal(double curBal) {
        this.curBal = curBal;
    }

    //declaration of abstract method
    public abstract double calcBalance();
    //toString() method
    public String toString()
    {
        return("\nAccount Number: "+getAccNo()+"\nCustomer Name: "+getCustName()+"\nGender: "+getCustGender()+"\nJob Position: "+getCustJob()+"\nCurrent Balance: "+getCurBal());
    }

}

/** derived class Savings from super class Bank**/
class Savings extends Bank{
    //instance variable
    private double savRate;
    //parameterized construtor
    public Savings(String no,String name,int gender,String job,double bal,double rate) {
        //calling the base class constructor
        super(no,name,gender,job,bal);
        savRate=rate;
    }
    //implementing the abstract method
    public double calcBalance()
    {
        //calculated the balance by applying the savRate to the current balance
        double balance=getCurBal()+(savRate*getCurBal());
        //updating the current balance with balance (to reflect the changes)
        setCurBal(balance);
        return balance;
    }

}
//derived from bank class
class Current extends Bank{

    //instance variable
    private boolean fixedDep; //whether the customer keeps the fixed
    private double curRate;

    //to keep track of number of objects created for this class
    private static int count=0;

    //parameterized constructor
    public Current(String no,String name,int gender,String job,double bal,boolean dep,double crate) {
        //calling the base class constructor
        super(no,name,gender,job,bal);
        fixedDep=dep;
        curRate=crate;
        //incrementing the count variable by 1
        count++;
    }

    //this method returns the value of count
    public static int getCount() {
        return count;
    }

    //this function calculates the balance of current account
    @Override
    public double calcBalance() {
        double balance;
        balance=getCurBal()+(curRate*getCurBal());
        setCurBal(balance);
        return balance;
    }

    //this function deducts the service charge if the customer is having the fixed deposit
    public void DeductingServiceCharge()
    {
        if(fixedDep==true)
            setCurBal(getCurBal()-150);
    }


}

// Main class which is used to test Bank,Savings and Current classes **/
 class BankMain {

    public static void main(String[] args) {

        //creates an array of objects for Bank class with size as 4
        Bank[] acct=new Bank[4];

        //creates 4 Bank accounts
        acct[0]=new Savings("S001","James Patrick Meegan",1,"Software Engineer",65000,0.20);
        acct[1]=new Savings("S002","David",1,"Retail Associate",2000,0.20);
        acct[2]=new Current("C001","Jack",1,"Marketing",500,true,0.10);
        acct[3]=new Current("C002","Cay",2,"Nurse",100000,false,0.15);

        //display the details of array acct
        System.out.println("Details of all customers of a bank");
        for(int i=0;i<acct.length;i++)
            System.out.println(acct[i].toString());

        //displays the number of current account customers of Bank by calling the static method getCount of Current class
        System.out.println("The number of customers who are having current account in bank is "+Current.getCount());

        //prompt the user to enter the account number to search
        Scanner input=new Scanner(System.in);
        boolean validAccount=false;
        System.out.print("\nEnter the customer account number to search: ");
        String accountnumber=input.nextLine();

        for(int i=0;i<acct.length;i++)
        {
            //if the user entered account number is available display the that account details
            if(accountnumber.equalsIgnoreCase(acct[i].getAccNo()))
            {
                System.out.println(acct[i].toString());
                validAccount=true;
            }
        }

        //if there is no account number like that display the error message
        if(!validAccount)
            System.out.println("The Entered Account number "+accountnumber+" is invalid");


    }

}





