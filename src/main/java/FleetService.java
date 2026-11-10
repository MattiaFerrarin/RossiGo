import Exceptions.CFInvalidException;
import Exceptions.InsufficientFundsException;
import Exceptions.UserNotFoundException;
import Model.User;
import Model.Vehicle;
import Model.Rent;
import it.kamaladafrica.codicefiscale.CodiceFiscale;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FleetService {
    HashMap<String, User> Users;
    HashMap<String, Vehicle> Vehicles;
    HashMap<String, Rent> Rents;


    private void LoadFromDatabase(){

    }

    private void UpdateDatabase(){

    }

    public void ExportRents() {
        String[] headers = {"User", "Vehicle", "StartTime", "EndTime"};

        try (FileWriter out = new FileWriter("rents.csv");
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
            for(Map.Entry<String, Rent> item : Rents.entrySet()){
                printer.printRecord(item.getValue().User().id(), item.getValue().Vehicle().Code(), item.getValue().StartTime().toString(), item.getValue().EndTime().toString());
            }
            printer.flush();
        } catch (IOException e) {
            System.out.println("Error while exporting");
        }
    }

    public void RegisterVehicle(Vehicle v){
        Vehicles.put(v.Code(), v);
        UpdateDatabase();
    }

    public void RegisterUser(User u) throws CFInvalidException, IllegalArgumentException {
        if(!CodiceFiscale.isFormatValid(u.CF())){
            throw new CFInvalidException("Invalid CF", u.CF());
        }
        if (StringUtils.isBlank(u.name())) {
            throw new IllegalArgumentException("The name can't be empty");
        }
        EmailValidator validator = EmailValidator.getInstance();
        if(!validator.isValid(u.email())){
            throw new IllegalArgumentException("Invalid email");
        }
        Users.put(u.id(), u);
        UpdateDatabase();
    }

    public void UnlockVehicle(String code, String userId) throws UserNotFoundException {
        User u = Users.get(userId);
        if (u == null) {
            throw new UserNotFoundException("User not found");
        }
        // Unlock vehicle (? What do I do?)
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