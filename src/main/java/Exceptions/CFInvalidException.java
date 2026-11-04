package Exceptions;

public class CFInvalidException extends RossiGoException {
    private String CF;
    public CFInvalidException(String msg, String cf){
        CF = cf;
        super(msg);
    }
    public CFInvalidException(String msg, Throwable cause, String cf){
        CF = cf;
        super(msg, cause);
    }
    public String GetCF(){
        return CF;
    }
}
