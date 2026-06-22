package com.amrapari.nhcsbackend.repository;

import com.amrapari.nhcsbackend.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUserId(Long userId);
}
