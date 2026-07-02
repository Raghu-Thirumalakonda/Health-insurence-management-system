package com.vendor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendor.entity.VendorEntity;
import com.vendor.model.VendorModel;
import com.vendor.repository.VendorRepository;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    // SAVE
    public VendorModel saveVendor(VendorModel model) {

        VendorEntity entity = mapToEntity(model);
        VendorEntity saved = vendorRepository.save(entity);

        return mapToModel(saved);
    }

    // GET BY ID
    public VendorModel getVendorById(Integer id) {
        return vendorRepository.findById(id)
                .map(this::mapToModel)
                .orElse(null);
    }

    // GET ALL
    public List<VendorModel> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    // UPDATE
    public VendorModel updateVendor(Integer id, VendorModel model) {

        VendorEntity entity = vendorRepository.findById(id).orElse(null);

        if (entity != null) {
            entity.setVendorName(model.getVendorName());
            entity.setCompanyName(model.getCompanyName());
            entity.setPhoneNumber(model.getPhoneNumber());
            entity.setEmail(model.getEmail());
            entity.setAddress(model.getAddress());

            return mapToModel(vendorRepository.save(entity));
        }

        return null;
    }

    // DELETE
    public String deleteVendor(Integer id) {
        vendorRepository.deleteById(id);
        return "Vendor deleted successfully";
    }

    // ===== MAPPING =====

    private VendorEntity mapToEntity(VendorModel model) {
        VendorEntity entity = new VendorEntity();

        entity.setVendorId(model.getVendorId());
        entity.setVendorName(model.getVendorName());
        entity.setCompanyName(model.getCompanyName());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setEmail(model.getEmail());
        entity.setAddress(model.getAddress());

        return entity;
    }

    private VendorModel mapToModel(VendorEntity entity) {
        VendorModel model = new VendorModel();

        model.setVendorId(entity.getVendorId());
        model.setVendorName(entity.getVendorName());
        model.setCompanyName(entity.getCompanyName());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setEmail(entity.getEmail());
        model.setAddress(entity.getAddress());

        return model;
    }
}