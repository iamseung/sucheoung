package com.sucheong.platform.enrollment;

import com.sucheong.platform.persistence.EnrollmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentJpaRepository extends JpaRepository<EnrollmentJpaEntity, Long> {

    List<EnrollmentJpaEntity> findAllByLectureId(Long lectureId);

    List<EnrollmentJpaEntity> findAllByMemberId(Long memberId);
}
