package Model;

import java.util.Objects;

public abstract class Vehicle {
    private final String Code;
    private Double BatteryLevel;
    private State State;

    public Vehicle(String Code, Double BatteryLevel, State State) {
        this.Code = Code;
        this.BatteryLevel = BatteryLevel;
        this.State = State;
    }

    public String Code() {
        return Code;
    }

    public Double BatteryLevel() {
        return BatteryLevel;
    }

    public void ChangeBatteryLevel(Double newBatteryLevel){
        if(newBatteryLevel >= 0.0 && newBatteryLevel <= 1.0){
            BatteryLevel = newBatteryLevel;
        }
    }

    public State State() {
        return State;
    }

    public void ChangeState(State newState){
        State = newState;
    }

    public abstract Double CalculateCost(int minutes);
    public abstract Double BatteryConsumptionPerMin();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Vehicle) obj;
        return Objects.equals(this.Code, that.Code) &&
                Objects.equals(this.BatteryLevel, that.BatteryLevel) &&
                Objects.equals(this.State, that.State);
    }

    @Override
    public String toString() {
        return "Vehicle[" +
                "Code=" + Code + ", " +
                "BatteryLevel=" + BatteryLevel + ", " +
                "State=" + State + ']';
    }
};