package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Enums.VaccinationCenterType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    int covaxinCount;
    int covishieldCount;
    int sputnikCount;
    String vaccinationCenterType;
    int patientCount;
    int doctorCount;
    @OneToMany(mappedBy = "vaccinationCenter" , cascade = CascadeType.ALL)
    List<Doctor> doctorList;
}
