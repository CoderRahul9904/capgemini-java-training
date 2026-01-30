package day9;

import java.util.Arrays;
import java.util.List;

public class Operation {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30, 35, 40);
        List<String> names = Arrays.asList("Sekhar", "Amit", "Anita", "Ravi", "Suman", "Ajay");
        System.out.println("Even Numbers : ");
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

        System.out.println("Odd Numbers : ");
        numbers.stream().filter(n -> n % 2 != 0).forEach(System.out::println);

        System.out.println("Multiply Each Number by 2 : ");
        numbers.stream().map(n -> n*2).forEach(System.out::println);

        System.out.println("Numbers greater than 25 : ");
        numbers.stream().filter(n -> n > 25).forEach(System.out::println);

        System.out.println("Total Numbers : ");


        System.out.println("Sum of all Numbers : ");



//        System.out.println("Max of all Numbers : ");
//        int max = numbers.stream().sum();
//        System.out.println(max);
//        
//        System.out.println("Min of all Numbers : ");
//        int min = numbers.stream().sum();
//        System.out.println(min);

        System.out.println("Convert all numbers to String : ");
        List<Integer> numsString = numbers.stream().toList();





    }
}