package com.vendor.repository;
import com.vendor.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PolicyRepository extends JpaRepository<PolicyEntity,Integer> {
    List<PolicyEntity> findByPolicyNameContainingIgnoreCase(String q);
}
