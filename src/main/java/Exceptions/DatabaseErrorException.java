package Exceptions;

public class DatabaseErrorException extends RossiGoException {
    public DatabaseErrorException(String msg){
        super(msg);
    }
    public DatabaseErrorException(String msg, Throwable cause){
        super(msg, cause);
    }
}
