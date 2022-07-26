package service;

public class OutOfOrderException extends Exception{
    public OutOfOrderException(String message) {
        super(message);
    }

    public OutOfOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
