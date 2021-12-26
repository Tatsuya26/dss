package src.data;

public class NotFoundInDBException extends Exception{
    public NotFoundInDBException() {
        super();
    }

    public NotFoundInDBException(String msg) {
        super(msg);
    }
}
