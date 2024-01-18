package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Repositories;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.Doctor;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.VaccinationCenter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, UUID> {
    @Query(value = "select * from vaccination_center where vaccination_center_type =:vaccinationCenterType and covaxin_count!=0 and patient_count= (select min(patient_count) from vaccination_center where vaccination_center_type=:vaccinationCenterType)",nativeQuery = true)
    public List<VaccinationCenter> getAllVCOntheBasisOfTypeAndCovaxinCount(String vaccinationCenterType);
    @Query(value = "select * from vaccination_center where vaccination_center_type =:vaccinationCenterType and covishield_count!=0 and patient_count= (select min(patient_count) from vaccination_center where vaccination_center_type=:vaccinationCenterType)",nativeQuery = true)
    public List<VaccinationCenter> getAllVCOntheBasisOfTypeAndCovishieldCount(String vaccinationCenterType);
    @Query(value = "select * from vaccination_center where vaccination_center_type =:vaccinationCenterType and sputnik_count!=0 and patient_count= (select min(patient_count) from vaccination_center where vaccination_center_type=:vaccinationCenterType)",nativeQuery = true)
    public List<VaccinationCenter> getAllVCOntheBasisOfTypeAndSputnikCount(String vaccinationCenterType);
    @Query(value = "select * from vaccination_center where doctor_count=(select min(doctor_count) from vaccination_center)",nativeQuery = true)
    public List<VaccinationCenter> getVCWithMinimumDoctors();
    @Transactional
    @Modifying
    @Query(value = "update vaccination_center set doctor_count=:count where id=:id",nativeQuery = true)
    public void updateDocCountByOne(int count,UUID id);
    @Transactional
    @Modifying
    @Query(value = "update vaccination_center set patient_count=:count where id=:id",nativeQuery = true)
    public void updatePatientCountByOne(int count,UUID id);
}
