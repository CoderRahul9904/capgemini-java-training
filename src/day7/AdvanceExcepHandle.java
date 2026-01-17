package day7;

public class AdvanceExcepHandle {
    public static int add(int a, int b) {
        int c=0;
        try{
            c=a/b;
            return c;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            return a;
        }
    }
    public static void main(String[] args) {
        System.out.println(add(10,0));
    }
}
