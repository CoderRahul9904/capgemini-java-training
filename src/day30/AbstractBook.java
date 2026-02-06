package day30;


abstract class Book{
    String title;
    abstract void setTitle(String s);
    String getTitle(){
        return title;
    }
}



class Novel extends Book{

    @Override
    void setTitle(String s) {
        this.title = s;
    }

}
public class AbstractBook {
    public static void main(String[] args) {

    }
}
