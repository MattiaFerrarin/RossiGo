import Model.User;
import Model.Vehicle;
import Model.Rent;

import java.util.HashMap;

public class FleetService {
    HashMap<String, User> Users;
    HashMap<String, Vehicle> Vehicles;
    HashMap<String, Rent> Rents;

    public void RegisterVehicle(Vehicle v){

    }

    public void RegisterUser(User u){
        // Kamala, exception
    }

    public void UnlockVehicle(String code, String userId){
        // ?
    }

    public void EndRent(String code, int minutesUsed){
        // 1€ + calccost(min), updates battery with batterypermin(min)
    }

    public void Recharge(String code){
        Vehicles.get(code).ChangeBatteryLevel(1.0);
    }
}
