package Persistence;

import Exceptions.*;
import java.sql.*;

public class Database {
    private Connection connection;
    private static Database instance = null;
    private Database(String url) throws DatabaseErrorException {
        try {
            connection = DriverManager.getConnection(url);
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

    public void InsertIdk(String name) throws DatabaseErrorException {
        if (!testConnection()){
            throw new DatabaseErrorException("Connection to the database was invalid");
        }

        String query = "INSERT INTO idk(idk) VALUES (?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseErrorException("Insert failed: " + e);
        }
    }
}
