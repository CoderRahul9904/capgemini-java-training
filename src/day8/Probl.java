package day8;

import java.util.Scanner;

@FunctionalInterface
interface StringLengthFunction {
    int getLength(String s);
}


public class Probl {
    static StringLengthFunction str=(String s) -> s.length();
    public static void main(String[] args) {
        // write java program
        System.out.println("Enter the Mother Tongue");
        Scanner obj = new Scanner(System.in);
        String motherTongue=obj.next();
        System.out.println("Mother Tongue's First Letter is: " + motherTongue.charAt(0));


        System.out.println(motherTongue.charAt(0) + motherTongue + motherTongue);

        StringBuilder sb=new StringBuilder(motherTongue);
        String reverseMotherTongue=sb.reverse().toString();
        System.out.println("The reverse of Mother Tongue is: " + reverseMotherTongue);

        if(reverseMotherTongue.equals(motherTongue)) System.out.println("Is Anagram");
        else System.out.println("Is Not Anagram");

        //lamda function

    }
}
