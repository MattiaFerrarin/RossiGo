package Exceptions;

public class RossiGoException extends Exception {
    public RossiGoException(String msg){
        super(msg);
    }
    public RossiGoException(String msg, Throwable cause){
        super(msg, cause);
    }
}
