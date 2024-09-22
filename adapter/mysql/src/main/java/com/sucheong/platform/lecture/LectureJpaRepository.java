package com.sucheong.platform.lecture;

import com.sucheong.platform.persistence.LectureJpaEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureJpaRepository extends JpaRepository<LectureJpaEntity, Long> {

    List<LectureJpaEntity> findAll(Sort sort);
}
