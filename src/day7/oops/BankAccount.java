package day7.oops;

import day7.oops.type.LoanType;

import static day7.oops.type.LoanType.SIMPLE;

public class BankAccount {
    private  int accountNumber;
    private  int balance;
    public String name;
    private boolean isActiveLoan=false;
    private int loanAmount;
    private LoanType loanType=SIMPLE;
    private int loanYear;

    public BankAccount(int accountNumber, int balance, String name, boolean isActiveLoan, int loanAmount, int loanYear) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.name = name;
        this.isActiveLoan = isActiveLoan;
        this.loanAmount = loanAmount;
        this.loanYear = loanYear;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanAmount() {
        return loanAmount;
    }
    public void setLoanYear(int loanYear) {
        this.loanYear = loanYear;
    }

    public int deposit(int amount) {
        return balance + amount;
    }
    public int withdraw(int amount) {
        if (balance - amount < 0) {
            System.out.println("Insufficient balance");
            return -1;
        }else{
            return balance - amount;
        }
    }

    public boolean isActiveLoan(){
        return isActiveLoan;
    }

    public void applyLoan(LoanType loanType, int loanAmount) {
        isActiveLoan=true;
        this.loanType=loanType;
        this.loanAmount=loanAmount;
    }

    public int getLoanYear() {
        return loanYear;
    }

    public double getInterestAmount() {
        if(!isActiveLoan) {
            System.out.println("No Loan Active");
            return 0;
        }else if(this.loanType!=SIMPLE) {
            return getComplexInterestAmount();
        }else{
            // else it simple interest
            return this.loanAmount*this.loanYear*0.3;
        }
    }

    private double getComplexInterestAmount() {
        return this.loanAmount*Math.pow(1+0.3,this.loanYear);
    }
    public static void main(String[] args) {
        BankAccount bka=new BankAccount(1,100000,"RAHUL",true,10000,1);
        System.out.println(bka.getBalance());
    }
}

// static method can call/use ---> static variable, static method
// non-static method can call/use ----> static and non-static methods and variable
