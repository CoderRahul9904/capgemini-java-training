package day2;

import java.util.Scanner;

public class Pattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter i: ");
        int row = sc.nextInt();
        System.out.println("Enter j: ");
        int column = sc.nextInt();

//        for(int i = 0; i <= row; i++) {
//            for(int j = column; j >=1; j--) {
//                System.out.print(j);
//            }
//            System.out.println();
//        }
        for(int i=1; i<=row; i++) {
            for(int j=1; j<=column; j++) {
                if(i>j) System.out.print(" ");
                else if((i+j)%2==0) System.out.print("0");
                else System.out.print("1");
            }
            System.out.println();
        }

    }
}
