package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Dto.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientLoginDTO {
    String email;
    String password;
}
