package practice;

class NotInLoveException extends Exception{
    public NotInLoveException(String msg){
        super(msg);
    }
}
public class CustomException {
    public static void main(String[] args) {
        int x=50;
        try{
            if(x > 80){
                System.out.println("She is in Love");
            }else{
                throw new NotInLoveException("She is not in Love");
            }
        }catch(NotInLoveException e){
            System.out.println(e);
        }
    }
}
