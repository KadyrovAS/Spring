package h2;

public class NotEnoughMoneyException extends Exception{
    NotEnoughMoneyException(String message){
        super(message);
    }
}