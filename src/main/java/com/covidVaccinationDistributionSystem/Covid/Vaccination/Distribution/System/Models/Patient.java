package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Enums.VaccinationCenterType;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Enums.VaccinationPreference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String gender;
    @Column(unique = true)
    Long phoneNumber;
    String address;
    @Column(unique = true)
    Long aadhaarNumber;
    int doseCount;
    @Column(unique = true)
    String email;
    String password;
    String vaccinationPreference;
    String vaccinationCenterType;
}
