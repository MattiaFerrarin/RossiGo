package Exceptions;

public class VehicleNotAvailableException extends RossiGoException {
    public VehicleNotAvailableException(String msg){
        super(msg);
    }
    public VehicleNotAvailableException(String msg, Throwable cause){
        super(msg, cause);
    }
}
