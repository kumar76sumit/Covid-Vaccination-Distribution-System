package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Repositories;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.Doctor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    @Transactional
    @Modifying
    @Query(value = "update doctor set patient_count=:count where id=:id",nativeQuery = true)
    public void updatePatientCountByOne(int count,UUID id);
    @Transactional
    @Modifying
    @Query(value = "insert into doctor_patient_list (doctor_id,patient_list_id) values(:docId,:patientId)",nativeQuery = true)
    public void addDoctorVsPatient(UUID docId,UUID patientId);
    @Query(value = "select * from doctor where vaccination_center_id=:id and patient_count=(select min(patient_count) from doctor where vaccination_center_id=:id)",nativeQuery = true)
    public List<Doctor> getMinimumPatientDoctorOnTheBasisOfVC(UUID id);
}
