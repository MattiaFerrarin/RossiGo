import Exceptions.CFInvalidException;
import Exceptions.InsufficientFundsException;
import Exceptions.UserNotFoundException;
import Model.User;
import Model.Vehicle;
import Model.Rent;
import it.kamaladafrica.codicefiscale.CodiceFiscale;

import java.util.HashMap;

public class FleetService {
    HashMap<String, User> Users;
    HashMap<String, Vehicle> Vehicles;
    HashMap<String, Rent> Rents;


    private void LoadFromDatabase(){

    }

    private void UpdateDatabase(){

    }

    public void RegisterVehicle(Vehicle v){
        Vehicles.put(v.Code(), v);
        UpdateDatabase();
    }

    public void RegisterUser(User u) throws CFInvalidException {
        if(!CodiceFiscale.isFormatValid(u.CF())){
            throw new CFInvalidException("Invalid CF", u.CF());
        }
        Users.put(u.id(), u);
        UpdateDatabase();
    }

    public void UnlockVehicle(String code, String userId) throws UserNotFoundException {
        User u = Users.get(userId);
        if (u == null) {
            throw new UserNotFoundException("User not found");
        }
        // ???
        UpdateDatabase();
    }

    public void EndRent(String code, int minutesUsed) throws InsufficientFundsException {
        Rent r = Rents.get(code);
        Double cost = 1 + r.Vehicle().CalculateCost(minutesUsed);
        r.Vehicle().ChangeBatteryLevel(r.Vehicle().BatteryLevel() - r.Vehicle().BatteryConsumptionPerMin()*minutesUsed);
        r.User().Charge(cost);
        UpdateDatabase();
    }

    public void Recharge(String code){
        Vehicles.get(code).ChangeBatteryLevel(1.0);
        UpdateDatabase();
    }
}
