package day7.oops;

abstract class Animal{
    public abstract String sound();
}
class Dog extends Animal{
    public String sound(){
        return "waoohhhhh";
    }
}
class Cat extends Animal{
    public String sound(){
        return "Meow";
    }
}

public class PolyMorphismEx {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();
        System.out.println(cat.sound());
    }

}
