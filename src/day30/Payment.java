package day30;

import java.util.List;

enum Status{
    PENDING, SUCCESS, FAILED
}
abstract class PaymentProcessor{
    private String transcationId;
    private double amount;
    public Status status;

    public PaymentProcessor(String transcationId, double amount) {
        this.transcationId = transcationId;
        this.amount = amount;
    }

    abstract boolean validatePayment();
    abstract boolean exceuteTransaction();
    public void printReceipt() {
        System.out.println("Transaction ID: " + transcationId);
        System.out.println("Amount: " + amount);
        System.out.println("Status: " + status);
    }
    public final void process() {
        if (validatePayment()) {
            status = Status.PENDING;
            boolean success = exceuteTransaction();
            status = success ? Status.SUCCESS : Status.FAILED;
            System.out.println(status);
        } else {
            status = Status.FAILED;
        }

        printReceipt();
    }
}

class CreditCardProcessor extends PaymentProcessor{

    private String cardNumber;
    CreditCardProcessor(String transcationId, double amount, String creditCardNumber){
        super(transcationId,amount);
        this.cardNumber = creditCardNumber;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    @Override
    boolean validatePayment() {
        if(getCardNumber() == null) return false;
        int cardNumberLength = getCardNumber().length();
        if(cardNumberLength == 16) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    boolean exceuteTransaction() {
        System.out.println("Executing credit card transaction...");
        return true;
    }

}

class CryptoProcessor extends PaymentProcessor{
    private String address;
    CryptoProcessor(String transcationId, double amount, String address){
        super(transcationId,amount);
        this.address = address;
    }


    @Override
    boolean validatePayment() {
        if(address == null) return false;
        if(address.charAt(0) == '0' && address.charAt(1) == 'x'){
            return true;
        }else{
            return false;
        }
    }

    @Override
    boolean exceuteTransaction() {
        System.out.println("Executing Crypto transaction...");
        return true;
    }
}
public class Payment {
    public static void main(String[] args) {
        CreditCardProcessor ccp=new CreditCardProcessor("72hd881d9",20000,"2723628401381283");
        ccp.process();
        CryptoProcessor cp=new CryptoProcessor("72hd8181d9",20000,"0x8hshgd89eb9284s");
        cp.process();
    }

}
