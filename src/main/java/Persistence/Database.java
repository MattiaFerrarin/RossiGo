package Persistence;

import Exceptions.*;
import Model.Rent;
import Model.User;
import Model.Vehicle;

import java.sql.*;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Connection connection;
    private static Database instance = null;
    private Database(String url) throws DatabaseErrorException {
        try {
            connection = DriverManager.getConnection(url);
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON");
            }
        } catch (SQLException e) {
            throw new DatabaseErrorException(e.getMessage());
        }
    }

    public static Database getInstance() throws DatabaseErrorException {
        if(instance == null){
            instance = new Database("jdbc:sqlite:rossigo.db");
        }
        return instance;
    }

    private boolean testConnection() {
        try{
            return connection.isValid(5);
        } catch (SQLException e){
            return false;
        }
    }

    public HashMap<String, User> loadUsers() throws DatabaseErrorException {
        if(!testConnection()){
            throw new DatabaseErrorException("Connection to the database was invalid");
        }
        HashMap<String, User> users = new HashMap<>();
        String query = "SELECT id, name, email, cf, balance FROM users";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("cf"),
                        rs.getDouble("balance")
                );
                users.put(user.id(), user);
            }
        } catch (SQLException e) {
            throw new DatabaseErrorException("Failed to load users: " + e.getMessage());
        }
        return users;
    }

    public HashMap<String, Vehicle> loadVehicles() throws DatabaseErrorException {
        if(!testConnection()){
            throw new DatabaseErrorException("Connection to the database was invalid");
        }
        HashMap<String, Vehicle> vehicles = new HashMap<>();
        String query = "SELECT code, battery_level, state FROM vehicles"; // Come gestisco i tipi di veicoli? Prova a vedere per ISA

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Come la gestisco sta roba?
                /*
                Vehicle vehicle = new Vehicle(
                        rs.getString("code"),
                        rs.getDouble("battery_level")
                );
                vehicles.put(vehicle.Code(), vehicle);*/
            }
        } catch (SQLException e) {
            throw new DatabaseErrorException("Failed to load vehicles: " + e.getMessage());
        }
        return vehicles;
    }

    public HashMap<String, Rent> loadRents(Map<String, User> users, Map<String, Vehicle> vehicles) throws DatabaseErrorException {
        if(!testConnection()){
            throw new DatabaseErrorException("Connection to the database was invalid");
        }
        HashMap<String, Rent> rents = new HashMap<>();
        String query = "SELECT id, user_id, vehicle_code, start_time, end_time FROM rents";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String id = rs.getString("id");
                User user = users.get(rs.getString("user_id"));
                Vehicle vehicle = vehicles.get(rs.getString("vehicle_code"));
                ZonedDateTime startTime = ZonedDateTime.parse(rs.getString("start_time"));
                ZonedDateTime endTime = rs.getString("end_time") != null ? ZonedDateTime.parse(rs.getString("end_time")) : null;

                if (user != null && vehicle != null) {
                    Rent rent = new Rent(user, vehicle, startTime, endTime);
                    rents.put(id, rent);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseErrorException("Failed to load rents: " + e.getMessage());
        }
        return rents;
    }

    // Save/Update

    public void saveUser(User u) throws DatabaseErrorException {
        if(!testConnection()){
            throw new DatabaseErrorException("Connection to the database was invalid");
        }
        String query = "INSERT OR REPLACE INTO users (id, name, email, cf, credit) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, u.id());
            pstmt.setString(2, u.name());
            pstmt.setString(3, u.email());
            pstmt.setString(4, u.CF());
            pstmt.setDouble(5, u.Credit());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseErrorException("Failed to save user: " + e.getMessage());
        }
    }

    public void saveVehicle(Vehicle v) throws DatabaseErrorException {
        if(!testConnection()){
            throw new DatabaseErrorException("Connection to the database was invalid");
        }
        String query = "INSERT OR REPLACE INTO vehicles (code, battery_level, state) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, v.Code());
            pstmt.setDouble(2, v.BatteryLevel());
            pstmt.setInt(3, v.State().ordinal());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseErrorException("Failed to save vehicle: " + e.getMessage());
        }
    }

    public void saveRent(Rent r) throws DatabaseErrorException {
        if(!testConnection()){
            throw new DatabaseErrorException("Connection to the database was invalid");
        }
        String query = "INSERT OR REPLACE INTO rents (user_id, vehicle_code, start_time, end_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, r.User().id());
            pstmt.setString(2, r.Vehicle().Code());
            pstmt.setString(3, r.StartTime().toString());
            pstmt.setString(4, r.EndTime() != null ? r.EndTime().toString() : null);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseErrorException("Failed to save rent: " + e.getMessage());
        }
    }
}
