package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Dto.Request;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Enums.VaccinationPreference;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientSignUpDTO {
    String name;
    String gender;
    Long phoneNumber;
    String address;
    Long aadhaarNumber;
    String email;
    String password;
    VaccinationPreference vaccinationPreference;
}
