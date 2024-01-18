package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Exceptions;

public class WrongCredentials extends RuntimeException{
    public WrongCredentials(String message) {
        super(message);
    }
}
