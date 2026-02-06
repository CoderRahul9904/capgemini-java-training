package day10;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    String dept;
    double salary;

    Employee(int id, String name, String dept, double salary) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public String toString() {
        return id + " - " + name + " - " + dept + " - " + salary;
    }
}

public class Stream_API_Problem {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Amit", "IT", 60000));
        employees.add(new Employee(2, "Riya", "HR", 45000));
        employees.add(new Employee(3, "Karan", "IT", 75000));
        employees.add(new Employee(4, "Neha", "Finance", 50000));
        employees.add(new Employee(5, "Vikram", "IT", 90000));
        employees.add(new Employee(6, "Pooja", "HR", 52000));

        System.out.println(employees);

        // salary > 50000
        System.out.println("Employees with salary > 50000:");
        employees.stream()
                .filter(e -> e.salary > 50000)
                .forEach(e -> System.out.println(e));

        // IT department
        System.out.println("\nEmployees from IT:");
        employees.stream()
                .filter(e -> e.dept.equals("IT"))
                .forEach(e -> System.out.println(e));

        // all names
        System.out.println("\nEmployee names:");
        employees.stream()
                .map(e -> e.name)
                .forEach(n -> System.out.println(n));

        // sort by salary
        System.out.println("\nSorted by salary:");
        employees.stream()
                .sorted((a, b) -> Double.compare(a.salary, b.salary))
                .forEach(e -> System.out.println(e));

        // sort by name then salary
        System.out.println("\nSorted by name then salary:");
        employees.stream()
                .sorted(Comparator
                        .comparing((Employee e) -> e.name)
                        .thenComparing(e -> e.salary))
                .forEach(e -> System.out.println(e));

        // highest salary
        System.out.println("\nHighest paid employee:");
        Employee high = employees.stream()
                .max(Comparator.comparing(e -> e.salary))
                .orElse(null);
        System.out.println(high);

        // lowest salary
        System.out.println("\nLowest paid employee:");
        Employee low = employees.stream()
                .min(Comparator.comparing(e -> e.salary))
                .orElse(null);
        System.out.println(low);

        // count per department
        System.out.println("\nCount by department:");
        Map<String, Long> map = employees.stream()
                .collect(Collectors.groupingBy(e -> e.dept,
                        Collectors.counting()));

        map.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}