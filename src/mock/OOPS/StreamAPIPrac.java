package mock.OOPS;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIPrac {

    // ===== Problem 1 Classes =====
    static class Trader {
        String name;
        String city;

        Trader(String name, String city) {
            this.name = name;
            this.city = city;
        }
    }

    static class Transaction {
        Trader trader;
        int year;
        int value;

        Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }
    }

    // ===== Problem 2 Class =====
    static class Fruit {
        String name;
        int calories;
        String color;
        int price;

        Fruit(String name, int calories, String color, int price) {
            this.name = name;
            this.calories = calories;
            this.color = color;
            this.price = price;
        }
    }

    // ===== Problem 3 Class =====
    static class News {
        int newsId;
        String commentByUser;
        String comment;

        News(int newsId, String commentByUser, String comment) {
            this.newsId = newsId;
            this.commentByUser = commentByUser;
            this.comment = comment;
        }
    }

    public static void main(String[] args) {

        // ================== Problem 1 ==================
        Trader t1 = new Trader("Raj", "Delhi");
        Trader t2 = new Trader("Aman", "Pune");
        Trader t3 = new Trader("Neha", "Delhi");
        Trader t4 = new Trader("Kiran", "Mumbai");

        List<Transaction> transactions = List.of(
                new Transaction(t1, 2011, 300),
                new Transaction(t2, 2012, 1000),
                new Transaction(t3, 2011, 400),
                new Transaction(t1, 2011, 700),
                new Transaction(t4, 2013, 950),
                new Transaction(t3, 2011, 200)
        );

        // TODO: Solve Problem 1 using Streams
        System.out.println("================== Problem 1.1 ==================");
        transactions
                .stream()
                .filter(n -> n.year == 2011)
                .sorted(Comparator.comparingInt(a -> a.value))
                .toList()
                .forEach(n -> System.out.println("Trader " + n.trader.name + " City " + n.trader.city + " Year " + n.year + " Value " + n.value));

        System.out.println("================== Problem 1.2 ==================");
        transactions
                .stream()
                .filter(n -> n.trader.city == "Delhi")
                .toList()
                .forEach(n -> System.out.println("Living in " + n.trader.city + " with Value " + n.value));

        System.out.println("================== Problem 1.3 ==================");
        transactions
                .stream()
                .max(Comparator.comparingInt(a -> a.value))
                .stream()
                .toList()
                .forEach(n -> System.out.println("Highest value is " + n.value));

        System.out.println("================== Problem 1.4 ==================");
        transactions
                .stream()
                .min(Comparator.comparingInt(a -> a.value))
                .stream()
                .toList()
                .forEach(n -> System.out.println("Lowest value is " + n.value));

        // ================== Problem 2 ==================
        List<Fruit> fruits = List.of(
                new Fruit("Apple", 95, "RED", 120),
                new Fruit("Banana", 105, "YELLOW", 60),
                new Fruit("Orange", 80, "ORANGE", 90),
                new Fruit("Strawberry", 50, "RED", 200),
                new Fruit("Grapes", 65, "GREEN", 140),
                new Fruit("Mango", 130, "YELLOW", 150)
        );

        // TODO: Solve Problem 2 using Streams
        System.out.println("================== Problem 2.1 ==================");
        fruits
                .stream()
                .sorted(Comparator.comparingInt(n -> n.calories))
                .toList()
                .forEach(n -> System.out.println("Lower calories :" + n.calories + " Name is " + n.name));

        System.out.println("================== Problem 2.2 ==================");
        fruits
                .stream()
                .sorted(Comparator.comparing(n -> n.color))
                .toList()
                .forEach(n -> System.out.println("Current calories :" + n.color + " Name is " + n.name));


        System.out.println("================== Problem 2.3 ==================");
        fruits
                .stream()
                .filter(n -> n.color == "RED")
                .sorted(Comparator.comparingInt(n -> n.price))
                .forEach(n -> System.out.println(n.color + " Name is " + n.name + " with Ascend Price is " + n.price));


        // ================== Problem 3 ==================
        List<News> newsList = List.of(
                new News(101, "Raj", "Budget looks good"),
                new News(102, "Aman", "Budget is bad"),
                new News(101, "Neha", "Budget is average"),
                new News(103, "Raj", "Election coming soon"),
                new News(101, "Raj", "Budget should improve"),
                new News(102, "Aman", "Not impressed"),
                new News(103, "Neha", "Budget discussion continues")
        );

        // TODO: Solve Problem 3 using Streams
        System.out.println("================== Problem 3.1 ==================");
        int newIdWithMaxCommentList=
                newsList
                .stream()
                .collect(Collectors.groupingBy(n -> n.newsId, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        System.out.println("New Id with max comments is " + newIdWithMaxCommentList);

        System.out.println("================== Problem 3.2 ==================");
        long budgeWordCount=newsList
                .stream()
                .filter(n -> n.comment.contains("Budget"))
                .count();
        System.out.println("Budge word count is " + budgeWordCount);

        System.out.println("================== Problem 3.3 ==================");
        String user=newsList.stream()
                .collect(Collectors.groupingBy(n -> n.commentByUser, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        System.out.println("User with max Comment is " + user);

        System.out.println("================== Problem 3.4 ==================");
        Map<String, Long> userCommentCount =newsList.stream()
                .collect(Collectors.groupingBy(n -> n.commentByUser, Collectors.counting()));



        userCommentCount.forEach((users, count) ->
                System.out.println(users + " -----------> " + count)
        );

        // ================== Problem 4 ==================
        List<Trader> traders = List.of(
                new Trader("Raj", "Pune"),
                new Trader("Aman", "Mumbai"),
                new Trader("Neha", "Indore"),
                new Trader("Kiran", "Pune"),
                new Trader("Sahil", "Delhi"),
                new Trader("Rohit", "Mumbai")
        );

        // TODO: Solve Problem 4 using Streams
        System.out.println("================== Problem 4.1 ==================");
        System.out.println("All Unique Cities");
        traders
                .stream()
                .map(n -> n.city)
                .distinct()
                .forEach(city -> System.out.println(city));

        System.out.println("================== Problem 4.2 ==================");
        traders.stream().filter(n -> n.city.equals("Pune")).sorted(Comparator.comparing(n -> n.name)).forEach(n -> System.out.println("Trader from Pune " + n.name));

        System.out.println("================== Problem 4.3 ==================");
        traders.stream().sorted((a,b) -> a.name.compareTo(b.name)).forEach(n -> System.out.println("Trader Name Sorted Form: " + n.name));


        System.out.println("================== Problem 4.4 ==================");
        traders.stream().filter(n -> n.city.equals("Indore")).forEach(n -> System.out.println("Trader from Indore " + n.name));

    }
}
