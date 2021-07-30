package h2;

public class UnknownAccountException extends Exception{
    UnknownAccountException(String message){
        super(message);
    }
}