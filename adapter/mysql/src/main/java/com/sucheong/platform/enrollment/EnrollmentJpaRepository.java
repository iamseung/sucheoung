package com.sucheong.platform.enrollment;

import com.sucheong.platform.persistence.EnrollmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentJpaRepository extends JpaRepository<EnrollmentJpaEntity, Long> {
}
