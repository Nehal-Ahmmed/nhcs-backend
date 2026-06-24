package com.amrapari.nhcsbackend.repository;

import com.amrapari.nhcsbackend.domain.RoleApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleApplicationRepository extends JpaRepository<RoleApplication, Long> {
    List<RoleApplication> findByUserId(Long userId);
}
