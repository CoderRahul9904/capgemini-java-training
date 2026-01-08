package day1;

import java.util.Scanner;

public class PowerOfTwo {
    public static void main(String[] args) {

        // Write a bitwise Logic code to power of 2
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println((n > 0) && ((n & (n - 1)) == 0));
    }
}
