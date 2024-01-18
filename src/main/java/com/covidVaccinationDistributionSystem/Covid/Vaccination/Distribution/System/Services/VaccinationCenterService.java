package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Services;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Enums.VaccinationCenterType;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Enums.VaccinationPreference;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.Doctor;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.VaccinationCenter;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Repositories.DoctorRepository;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Repositories.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    @Autowired
    DoctorRepository doctorRepository;
    public ResponseEntity<VaccinationCenter> addVaccinationCenter(VaccinationCenter vaccinationCenter) {
        vaccinationCenterRepository.save(vaccinationCenter);
        return new ResponseEntity<>(vaccinationCenter, HttpStatus.CREATED);
    }
    public List<VaccinationCenter> getAllVCOntheBasisOfTypeAndPreference(String vaccinationCenterType, String vaccinationPreference) {
        if(vaccinationPreference.equals("Covaxin")) return vaccinationCenterRepository.getAllVCOntheBasisOfTypeAndCovaxinCount(vaccinationCenterType);
        else if(vaccinationPreference.equals("Covishield")) return vaccinationCenterRepository.getAllVCOntheBasisOfTypeAndCovishieldCount(vaccinationCenterType);
        else return vaccinationCenterRepository.getAllVCOntheBasisOfTypeAndSputnikCount(vaccinationCenterType);
    }
    public void updatePatientCountByOne(UUID id) {
        int patientCount=vaccinationCenterRepository.findById(id).get().getPatientCount()+1;
        vaccinationCenterRepository.updatePatientCountByOne(patientCount,id);
    }
}
