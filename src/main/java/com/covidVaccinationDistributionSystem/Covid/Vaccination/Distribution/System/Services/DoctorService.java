package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Services;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.Doctor;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.VaccinationCenter;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Repositories.DoctorRepository;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Repositories.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    public ResponseEntity<Doctor> addDoctor(Doctor doctor) {
        List<VaccinationCenter> vaccinationCenterList=vaccinationCenterRepository.getVCWithMinimumDoctors();
        doctor.setVaccinationCenter(vaccinationCenterList.get(0));
        vaccinationCenterRepository.updateDocCountByOne(vaccinationCenterList.get(0).getDoctorCount()+1,vaccinationCenterList.get(0).getId());
        doctorRepository.save(doctor);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }
    public List<Doctor> getMinimumPatientDoctorOnTheBasisOfVC(UUID id) {
        return doctorRepository.getMinimumPatientDoctorOnTheBasisOfVC(id);
    }
    public void updatePatientCountByOne(UUID id) {
        int patientCount=doctorRepository.findById(id).get().getPatientCount()+1;
        doctorRepository.updatePatientCountByOne(patientCount,id);
    }
    public void addDoctorVsPatient(UUID docId,UUID patientId) {
        doctorRepository.addDoctorVsPatient(docId,patientId);
    }
}
