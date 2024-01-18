package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
