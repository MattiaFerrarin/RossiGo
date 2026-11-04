package Exceptions;

public class UserNotFoundException extends RossiGoException {
    public UserNotFoundException(String msg){
        super(msg);
    }
    public UserNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
