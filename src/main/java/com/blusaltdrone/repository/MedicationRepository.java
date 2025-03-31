package com.blusaltdrone.repository;

import com.blusaltdrone.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}

