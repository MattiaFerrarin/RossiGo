package Model;

public class Bike extends Vehicle {
    private final int GearsNumber;

    public Bike(String Code, Double BatteryLevel, State State, int GearsNum) {
        super(Code, BatteryLevel, State);
        GearsNumber = GearsNum;
    }

    public int GearsNumber(){
        return GearsNumber;
    }

    @Override
    public Double CalculateCost(int minutes) {
        return 0.15*minutes;
    }

    @Override
    public Double BatteryConsumptionPerMin() {
        return 0.01;
    }
}
