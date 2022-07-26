package dao;

public class VMPersistenceException extends Exception{
    public VMPersistenceException(String message) {
        super(message);
    }

    public VMPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
