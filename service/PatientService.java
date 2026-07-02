package com.vendor.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendor.entity.PatientEntity;
import com.vendor.model.PatientModel;
import com.vendor.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // SAVE
    public PatientModel savePatient(PatientModel model) {

        PatientEntity entity = mapToEntity(model);
        PatientEntity saved = patientRepository.save(entity);

        return mapToModel(saved);
    }

    // GET BY ID
    public PatientModel getPatientById(Integer id) {
        return patientRepository.findById(id)
                .map(this::mapToModel)
                .orElse(null);
    }

    // GET ALL
    public List<PatientModel> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    // UPDATE
    public PatientModel updatePatient(Integer id, PatientModel model) {

        PatientEntity entity = patientRepository.findById(id).orElse(null);

        if (entity != null) {
            entity.setPatientName(model.getPatientName());
            entity.setAge(model.getAge());
            entity.setGender(model.getGender());
            entity.setDisease(model.getDisease());
            entity.setPhoneNumber(model.getPhoneNumber());

            return mapToModel(patientRepository.save(entity));
        }

        return null;
    }

    // DELETE
    public String deletePatient(Integer id) {
        patientRepository.deleteById(id);
        return "Patient deleted successfully";
    }

    // ===== MAPPERS =====

    private PatientEntity mapToEntity(PatientModel model) {
        PatientEntity entity = new PatientEntity();

        entity.setPatientId(model.getPatientId());
        entity.setPatientName(model.getPatientName());
        entity.setAge(model.getAge());
        entity.setGender(model.getGender());
        entity.setDisease(model.getDisease());
        entity.setPhoneNumber(model.getPhoneNumber());

        return entity;
    }

    private PatientModel mapToModel(PatientEntity entity) {
        PatientModel model = new PatientModel();

        model.setPatientId(entity.getPatientId());
        model.setPatientName(entity.getPatientName());
        model.setAge(entity.getAge());
        model.setGender(entity.getGender());
        model.setDisease(entity.getDisease());
        model.setPhoneNumber(entity.getPhoneNumber());

        return model;
    }
}