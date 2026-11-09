package Model;

import Exceptions.InsufficientFundsException;

import java.util.Objects;

public final class User {
    private String id;
    private String name;
    private String email;
    private String CF;
    private Double Credit;

    public User(String id, String name, String email, String CF, Double Credit) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.CF = CF;
        this.Credit = Credit;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String CF() {
        return CF;
    }

    public Double Credit() {
        return Credit;
    }

    public void Charge(Double cost) throws InsufficientFundsException{
        if(Credit() < cost){
            throw new InsufficientFundsException("Insufficient funds for user", Credit());
        }
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "email=" + email + ", " +
                "CF=" + CF + ", " +
                "Credit=" + Credit + ']';
    }
}
