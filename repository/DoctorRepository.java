package com.vendor.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.vendor.entity.DoctorEntity;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Integer>{

}