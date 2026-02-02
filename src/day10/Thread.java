package day10;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Thread {
    private String name;
    private BigDecimal price;

    public Thread(String name , String price){
        this.name = name;
        this.price = new BigDecimal(price);
    }

    public BigDecimal getRoundedPrice(int decimalPlaces){
        return price.setScale(decimalPlaces, RoundingMode.UP);
    }

    public static void main(String[] args) {
        Thread item = new Thread("Widget", "10.1234");

        BigDecimal rounded = item.getRoundedPrice(2);
        System.out.println("Original Price : " + item.price);
        System.out.println("Rounded Price : " + rounded);
    }
}