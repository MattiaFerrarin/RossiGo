package Model;

import java.time.ZonedDateTime;

public record Rent(User User, Vehicle Vehicle, ZonedDateTime StartTime, ZonedDateTime EndTime){}