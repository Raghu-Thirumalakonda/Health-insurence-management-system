package com.vendor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendor.entity.DoctorEntity;
import com.vendor.model.DoctorModel;
import com.vendor.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Save
    public DoctorModel saveDoctor(DoctorModel model) {

        DoctorEntity entity = mapToEntity(model);
        DoctorEntity saved = doctorRepository.save(entity);

        return mapToModel(saved);
    }

    // Get by ID
    public DoctorModel getDoctorById(Integer id) {
        return doctorRepository.findById(id)
                .map(this::mapToModel)
                .orElse(null);
    }

    // Get all
    public List<DoctorModel> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    // Update
    public DoctorModel updateDoctor(Integer id, DoctorModel model) {

        DoctorEntity entity = doctorRepository.findById(id).orElse(null);

        if (entity != null) {
            entity.setDoctorName(model.getDoctorName());
            entity.setSpecialization(model.getSpecialization());
            entity.setExperience(model.getExperience());
            entity.setPhoneNumber(model.getPhoneNumber());
            entity.setEmail(model.getEmail());

            return mapToModel(doctorRepository.save(entity));
        }

        return null;
    }

    // Delete
    public String deleteDoctor(Integer id) {
        doctorRepository.deleteById(id);
        return "Deleted successfully";
    }

    // ===== MAPPING METHODS =====

    private DoctorEntity mapToEntity(DoctorModel model) {
        DoctorEntity entity = new DoctorEntity();

        entity.setDoctorId(model.getDoctorId());
        entity.setDoctorName(model.getDoctorName());
        entity.setSpecialization(model.getSpecialization());
        entity.setExperience(model.getExperience());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setEmail(model.getEmail());

        return entity;
    }

    private DoctorModel mapToModel(DoctorEntity entity) {
        DoctorModel model = new DoctorModel();

        model.setDoctorId(entity.getDoctorId());
        model.setDoctorName(entity.getDoctorName());
        model.setSpecialization(entity.getSpecialization());
        model.setExperience(entity.getExperience());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setEmail(entity.getEmail());

        return model;
    }
}