package Exceptions;

public class InsufficientFundsException extends RossiGoException {
    private Double funds;
    public InsufficientFundsException(String msg, Double funds){
        this.funds = funds;
        super(msg);
    }
    public InsufficientFundsException(String msg, Throwable cause, Double funds){
        this.funds = funds;
        super(msg, cause);
    }
    public Double GetFunds(){
        return funds;
    }
}
