package src.data;


public class IdentifierAlreadyInDBException extends Exception{
    public IdentifierAlreadyInDBException() {
        super();
    }

    public IdentifierAlreadyInDBException(String msg) {
        super(msg);
    }
}
