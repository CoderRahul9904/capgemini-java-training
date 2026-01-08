package day1;

import java.util.Scanner;

public class ToggleNum {
    public static void main(String[] args) {
        // Toggle nth Bit of Number
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int num = sc.nextInt();
        System.out.println("Enter Nth Bit to Toggle: ");
        int bit = sc.nextInt();
        int alpha=num^(1<<bit);
        System.out.println(alpha);
    }
}
