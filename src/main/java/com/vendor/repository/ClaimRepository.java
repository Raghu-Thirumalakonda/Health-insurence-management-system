package com.vendor.repository;
import com.vendor.entity.ClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ClaimRepository extends JpaRepository<ClaimEntity,Integer> {
    List<ClaimEntity> findTop5ByOrderByIdDesc();
}
