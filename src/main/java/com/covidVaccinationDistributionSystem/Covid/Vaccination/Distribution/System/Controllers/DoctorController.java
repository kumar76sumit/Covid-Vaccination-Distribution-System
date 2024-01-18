package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Controllers;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.Doctor;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @PostMapping("/addDoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }
}
