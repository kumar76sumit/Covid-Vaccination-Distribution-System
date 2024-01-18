package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Controllers;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Dto.Request.PatientLoginDTO;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Dto.Request.PatientSignUpDTO;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Dto.Response.AppointmentBookedDTO;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Dto.Response.GeneralMessageDTO;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Enums.VaccinationCenterType;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Exceptions.UserNotFoundException;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Exceptions.WrongCredentials;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.Patient;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping("/signUp")
    public ResponseEntity<Patient> patientSignUp(@RequestBody PatientSignUpDTO patientSignUpDTO) {
        return patientService.patientSignUp(patientSignUpDTO);
    }
    @PostMapping("/login")
    public ResponseEntity patientLogin(@RequestBody PatientLoginDTO patientLoginDTO) {
        try {
            Patient patient=patientService.patientLogin(patientLoginDTO);
            return new ResponseEntity(patient, HttpStatus.OK);
        } catch (UserNotFoundException userNotFoundException) {
            return new ResponseEntity(new GeneralMessageDTO(userNotFoundException.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (WrongCredentials wrongCredentials) {
            return new ResponseEntity(new GeneralMessageDTO(wrongCredentials.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/bookAppointment")
    public ResponseEntity bookAppointment(@RequestParam String email, @RequestParam VaccinationCenterType vaccinationCenterType) {
        AppointmentBookedDTO appointmentBookedDTO=patientService.bookAppointment(email,vaccinationCenterType.toString());
        return new ResponseEntity(appointmentBookedDTO,HttpStatus.OK);
    }
}
