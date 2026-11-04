package Model;

public class Scooter extends Vehicle {
    private final int MaxSpeed;

    public Scooter(String Code, Double BatteryLevel, State State, int maxSpeed) {
        super(Code, BatteryLevel, State);
        MaxSpeed = maxSpeed;
    }

    public int MaxSpeed(){
        return MaxSpeed;
    }

    @Override
    public Double CalculateCost(int minutes) {
        return 0.25*minutes;
    }

    @Override
    public Double BatteryConsumptionPerMin() {
        return 0.025;
    }
}
