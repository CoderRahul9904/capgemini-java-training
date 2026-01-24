package day7.oops;

abstract class Vehicle {
    abstract void move();  // wheelers
    abstract void color();
    abstract void speed();
}

class Car extends Vehicle {

    @Override
    void move() {
        System.out.println("Car moves on four wheels");
    }

    @Override
    void color() {
        System.out.println("Car color is Red");
    }

    @Override
    void speed() {
        System.out.println("Car speed is 180 km/h");
    }
}

class Bike extends Vehicle {

    @Override
    void move() {
        System.out.println("Bike moves on two wheels");
    }

    @Override
    void color() {
        System.out.println("Bike color is Black");
    }

    @Override
    void speed() {
        System.out.println("Bike speed is 120 km/h");
    }
}
class Truck extends Vehicle {

    @Override
    void move() {
        System.out.println("Truck moves with heavy load");
    }

    @Override
    void color() {
        System.out.println("Truck color is Blue");
    }

    @Override
    void speed() {
        System.out.println("Truck speed is 90 km/h");
    }
}


public class VehicleAbtract {
    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle bike = new Bike();
        Vehicle truck = new Truck();

        car.move();
        car.color();
        car.speed();

        System.out.println();

        bike.move();
        bike.color();
        bike.speed();

        System.out.println();

        truck.move();
        truck.color();
        truck.speed();
    }
}
