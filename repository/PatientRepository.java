package com.vendor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.vendor.entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity,Integer>{

}