package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }
}
public class ComparatorPrac {
    public static void main(String[] args) {
        Comparator<String> cm=new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        };

        List<Student> students=new ArrayList<Student>();
        students.add(new Student("Roch1", 20));
        students.add(new Student("Oggy", 19));
        students.add(new Student("Roch2", 21));
        students.add(new Student("Jack", 18));

        Collections.sort(students,(a,b) -> a.age-b.age);
        for(Student s:students)
            System.out.println(s);
        List<String> ls=new ArrayList<String>();
        ls.add("Ahashja");
        ls.add("Bshuajassded");
        ls.add("Cdklod");
        ls.add("Dskkdmdmm");

        Collections.sort(ls,(a,b) -> a.length()-b.length());
        System.out.println(ls);
    }
}
