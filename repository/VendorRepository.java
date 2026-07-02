package com.vendor.repository;
	


import org.springframework.data.jpa.repository.JpaRepository;
import com.vendor.entity.VendorEntity;

public interface VendorRepository extends JpaRepository<VendorEntity, Integer> {

}