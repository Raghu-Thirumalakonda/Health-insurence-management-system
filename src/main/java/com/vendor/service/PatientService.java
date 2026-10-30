package com.vendor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.vendor.entity.PatientEntity;
import com.vendor.model.PatientModel;
import com.vendor.repository.PatientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) { this.patientRepository = patientRepository; this.passwordEncoder = passwordEncoder; }

    public PatientModel savePatient(PatientModel model) {
        PatientEntity entity = model.getPatientId() == null ? new PatientEntity() : patientRepository.findById(model.getPatientId()).orElseGet(PatientEntity::new);
        copy(model, entity);
        if (entity.getRegisteredAt() == null) entity.setRegisteredAt(LocalDate.now());
        return mapToModel(patientRepository.save(entity));
    }
    public PatientModel getPatientById(Integer id) { return patientRepository.findById(id).map(this::mapToModel).orElse(null); }
    public PatientModel getPatientByEmail(String email) { return patientRepository.findByEmailIgnoreCase(email).filter(PatientEntity::isPortalEnabled).map(this::mapToModel).orElse(null); }
    public List<PatientModel> getAllPatients() { return patientRepository.findAll().stream().map(this::mapToModel).collect(Collectors.toList()); }
    public PatientModel updatePatient(Integer id, PatientModel model) { model.setPatientId(id); return savePatient(model); }
    public String deletePatient(Integer id) { patientRepository.deleteById(id); return "Patient deleted successfully"; }
    public boolean changePortalPassword(String email, String currentPassword, String newPassword) {
        return patientRepository.findByEmailIgnoreCase(email).filter(PatientEntity::isPortalEnabled).map(patient -> {
            if (patient.getPasswordHash() == null || !passwordEncoder.matches(currentPassword, patient.getPasswordHash())) return false;
            patient.setPasswordHash(passwordEncoder.encode(newPassword));
            patientRepository.save(patient);
            return true;
        }).orElse(false);
    }

    private void copy(PatientModel m, PatientEntity e) {
        if (m.getEmail() != null && !m.getEmail().isBlank()) {
            patientRepository.findByEmailIgnoreCase(m.getEmail().trim()).ifPresent(existing -> {
                if (!existing.getPatientId().equals(e.getPatientId())) throw new IllegalArgumentException("That email is already assigned to another patient.");
            });
        }
        String submittedPassword = m.getPortalPassword();
        if (submittedPassword != null && !submittedPassword.isBlank()) {
            if (submittedPassword.length() < 10) throw new IllegalArgumentException("Patient portal passwords must be at least 10 characters.");
            e.setPasswordHash(passwordEncoder.encode(submittedPassword));
        }
        if (m.isPortalEnabled() && (m.getEmail() == null || m.getEmail().isBlank() || e.getPasswordHash() == null)) {
            throw new IllegalArgumentException("To enable patient access, add a unique email and set an initial password of at least 10 characters.");
        }
        e.setPortalEnabled(m.isPortalEnabled());
        e.setPatientName(m.getPatientName()); e.setAge(m.getAge()); e.setGender(m.getGender());
        e.setDisease(m.getDisease()); e.setPhoneNumber(m.getPhoneNumber());
        e.setEmail(m.getEmail() == null || m.getEmail().isBlank() ? null : m.getEmail().trim());
        if (m.getRegisteredAt() != null) e.setRegisteredAt(m.getRegisteredAt());
        e.setLastVisitDate(m.getLastVisitDate()); e.setProfileImageUrl(m.getProfileImageUrl());
        e.setInsurancePlan(m.getInsurancePlan()); e.setPolicyNumber(m.getPolicyNumber());
        e.setCoverageStartDate(m.getCoverageStartDate()); e.setCoverageEndDate(m.getCoverageEndDate());
        e.setNotificationPreference(m.getNotificationPreference());
    }
    private PatientModel mapToModel(PatientEntity e) {
        PatientModel m = new PatientModel();
        m.setPatientId(e.getPatientId()); m.setPatientName(e.getPatientName()); m.setAge(e.getAge()); m.setGender(e.getGender());
        m.setDisease(e.getDisease()); m.setPhoneNumber(e.getPhoneNumber()); m.setEmail(e.getEmail()); m.setRegisteredAt(e.getRegisteredAt());
        m.setLastVisitDate(e.getLastVisitDate()); m.setProfileImageUrl(e.getProfileImageUrl()); m.setInsurancePlan(e.getInsurancePlan());
        m.setPolicyNumber(e.getPolicyNumber()); m.setCoverageStartDate(e.getCoverageStartDate()); m.setCoverageEndDate(e.getCoverageEndDate());
        m.setNotificationPreference(e.getNotificationPreference()); m.setPortalEnabled(e.isPortalEnabled()); return m;
    }
}
