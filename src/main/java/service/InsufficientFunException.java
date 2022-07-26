package service;

public class InsufficientFunException extends Exception{
    public InsufficientFunException(String message) {
        super(message);
    }

    public InsufficientFunException(String message, Throwable cause) {
        super(message, cause);
    }
}
