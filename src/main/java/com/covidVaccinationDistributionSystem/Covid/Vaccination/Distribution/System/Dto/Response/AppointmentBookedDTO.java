package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Dto.Response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentBookedDTO {
    String patientName;
    int doseCount;
    String doctorName;
    String vaccinationCenterType;
    String vaccinationCenterName;
}
