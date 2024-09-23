package com.sucheong.platform.lecture;

import com.sucheong.platform.persistence.LectureJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<LectureJpaEntity, Long> {
}
