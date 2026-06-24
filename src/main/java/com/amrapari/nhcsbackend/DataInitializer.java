package com.amrapari.nhcsbackend;

import com.amrapari.nhcsbackend.domain.Role;
import com.amrapari.nhcsbackend.domain.User;
import com.amrapari.nhcsbackend.domain.Hospital;
import com.amrapari.nhcsbackend.domain.Patient;
import com.amrapari.nhcsbackend.domain.Doctor;
import com.amrapari.nhcsbackend.repository.UserRepository;
import com.amrapari.nhcsbackend.repository.HospitalRepository;
import com.amrapari.nhcsbackend.repository.PatientRepository;
import com.amrapari.nhcsbackend.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Mock users have been removed per user request
        
        createMockHospitalsIfEmpty();
    }

    private void createMockUserIfNotExists(String username, String email, java.util.Set<Role> roles) {
        if (userRepository.findByUsername(username).isEmpty()) {
            User user = User.builder()
                    .username(username)
                    .email(email)
                    .password(passwordEncoder.encode("password123"))
                    .roles(new java.util.HashSet<>(roles))
                    .build();
            userRepository.save(user);

            if (roles.contains(Role.PATIENT)) {
                Patient patient = Patient.builder()
                        .user(user)
                        .fullName(capitalize(username) + " Mock Citizen")
                        .contactNumber("+8801700000001")
                        .gender("Male")
                        .address("Dhaka, Bangladesh")
                        .build();
                patientRepository.save(patient);
            }

            if (roles.contains(Role.DOCTOR)) {
                Doctor doctor = Doctor.builder()
                        .user(user)
                        .fullName("Dr. " + capitalize(username))
                        .specialization("General Physician & Outbreak Expert")
                        .licenseNumber("MBBS-54321")
                        .contactNumber("+8801799999999")
                        .hospitalAffiliation("Dhaka Medical College Hospital")
                        .build();
                doctorRepository.save(doctor);
            }
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return "";
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private void createMockHospitalsIfEmpty() {
        if (hospitalRepository.count() == 0) {
            hospitalRepository.save(Hospital.builder()
                    .facilityId("FAC-1001")
                    .name("Dhaka Medical College Hospital")
                    .division("Dhaka")
                    .classification("Public")
                    .totalBeds(2600)
                    .occupiedBeds(2450)
                    .complianceScore(88)
                    .status("Active")
                    .build());
            
            hospitalRepository.save(Hospital.builder()
                    .facilityId("FAC-1002")
                    .name("Square Hospitals Ltd.")
                    .division("Dhaka")
                    .classification("Private")
                    .totalBeds(400)
                    .occupiedBeds(320)
                    .complianceScore(98)
                    .status("Active")
                    .build());
                    
            hospitalRepository.save(Hospital.builder()
                    .facilityId("FAC-1003")
                    .name("Chittagong Medical College")
                    .division("Chattogram")
                    .classification("Public")
                    .totalBeds(1313)
                    .occupiedBeds(1200)
                    .complianceScore(72)
                    .status("Under Review")
                    .build());
                    
            hospitalRepository.save(Hospital.builder()
                    .facilityId("FAC-1004")
                    .name("Sylhet MAG Osmani Medical")
                    .division("Sylhet")
                    .classification("Public")
                    .totalBeds(900)
                    .occupiedBeds(850)
                    .complianceScore(85)
                    .status("Active")
                    .build());
        }
    }
}
