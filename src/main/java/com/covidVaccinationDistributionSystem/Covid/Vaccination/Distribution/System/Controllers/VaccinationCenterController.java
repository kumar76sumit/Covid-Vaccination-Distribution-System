package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Controllers;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.VaccinationCenter;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Services.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vc")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService vaccinationCenterService;
    @PostMapping("/addVaccinationCenter")
    public ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter) {
        return vaccinationCenterService.addVaccinationCenter(vaccinationCenter);
    }
}
