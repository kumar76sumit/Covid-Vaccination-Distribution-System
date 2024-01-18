package com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Services;

import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Dto.Request.PatientLoginDTO;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Dto.Request.PatientSignUpDTO;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Dto.Response.AppointmentBookedDTO;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Exceptions.UserNotFoundException;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Exceptions.WrongCredentials;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.Doctor;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.Patient;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Models.VaccinationCenter;
import com.covidVaccinationDistributionSystem.Covid.Vaccination.Distribution.System.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    VaccinationCenterService vaccinationCenterService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    MailService mailService;
    public ResponseEntity<Patient> patientSignUp(PatientSignUpDTO patientSignUpDTO) {
        Patient patient=new Patient();
        patient.setName(patientSignUpDTO.getName());
        patient.setGender(patientSignUpDTO.getGender());
        patient.setPhoneNumber(patientSignUpDTO.getPhoneNumber());
        patient.setAddress(patientSignUpDTO.getAddress());
        patient.setAadhaarNumber(patientSignUpDTO.getAadhaarNumber());
        patient.setEmail(patientSignUpDTO.getEmail());
        patient.setPassword(patientSignUpDTO.getPassword());
        patient.setVaccinationPreference(patientSignUpDTO.getVaccinationPreference().toString());
        patientRepository.save(patient);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }
    public Patient patientLogin(PatientLoginDTO patientLoginDTO) {
        String email=patientLoginDTO.getEmail();
        String password=patientLoginDTO.getPassword();
        Patient patient=patientRepository.findByEmail(email);
        if(patient==null) throw new UserNotFoundException("Patient email does not exist in our DB");
        if(!patient.getPassword().equals(password)) throw new WrongCredentials("Incorrect password!");
        return patient;
    }
    public AppointmentBookedDTO bookAppointment(String email,String vaccinationCenterType) {
        Patient patient=patientRepository.findByEmail(email);
        String vaccinationPreference=patient.getVaccinationPreference();
        List<VaccinationCenter> vaccinationCenterList=vaccinationCenterService.getAllVCOntheBasisOfTypeAndPreference(vaccinationCenterType,vaccinationPreference);
        VaccinationCenter vaccinationCenter=vaccinationCenterList.get(0);
        List<Doctor> doctorList=doctorService.getMinimumPatientDoctorOnTheBasisOfVC(vaccinationCenter.getId());
        Doctor doctor=doctorList.get(0);
        updateDoseCountByOne(patient);
        doctorService.updatePatientCountByOne(doctor.getId());
        vaccinationCenterService.updatePatientCountByOne(vaccinationCenter.getId());
        doctor.getPatientList().add(patient);
        doctorService.addDoctorVsPatient(doctor.getId(),patient.getId());
        AppointmentBookedDTO appointmentBookedDTO=new AppointmentBookedDTO();
        appointmentBookedDTO.setPatientName(patient.getName());
        appointmentBookedDTO.setDoseCount(patient.getDoseCount()+1);
        appointmentBookedDTO.setDoctorName(doctor.getName());
        appointmentBookedDTO.setVaccinationCenterType(vaccinationCenter.getVaccinationCenterType());
        appointmentBookedDTO.setVaccinationCenterName(vaccinationCenter.getName());

        String to=patient.getEmail();
        String sub=String.format("Congratulations !! %s your appointment got created", patient.getName());
        String text = String.format("Hii %s," +
                        "\nYour appointment got created. Below are your appointment details :" +
                        "\n1. Dose Count : %d" +
                        "\n2. Doctor Name : %s" +
                        "\n3. Vaccination Center Type : %s" +
                        "\n4. Vaccine Type : %s" +
                        "\n5. Vaccination Center Name : %s " +
                        "\n- CVDS",
                patient.getName(),
                patient.getDoseCount()+1,
                doctor.getName(),
                vaccinationCenter.getVaccinationCenterType(),
                patient.getVaccinationPreference(),
                vaccinationCenter.getName()
        );
        mailService.generateMail(to,sub,text);
        return appointmentBookedDTO;
    }
    public void updateDoseCountByOne(Patient patient) {
        int doseCount=patient.getDoseCount()+1;
        patientRepository.updateDoseCountByOne(doseCount,patient.getId());
    }
}
