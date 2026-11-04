package Exceptions;

public class LowBatteryException extends RossiGoException {
    private Float Battery;
    public LowBatteryException(String msg, Float battery){
        Battery = battery;
        super(msg);
    }
    public LowBatteryException(String msg, Throwable cause, Float battery){
        Battery = battery;
        super(msg, cause);
    }
}
