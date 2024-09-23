package com.sucheong.platform.lecture;

import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.persistence.LectureJpaEntity;
import com.sucheong.platform.port.LecturePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LectureAdapter implements LecturePort {

    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public Lecture findById(Long lectureId) {
        LectureJpaEntity lectureJpaEntity = lectureJpaRepository.findById(lectureId).orElse(null);

        if (lectureJpaEntity == null) {
            return null;
        }
        return this.toModel(lectureJpaEntity);
    }

    @Override
    public List<Lecture> findByIds(List<Long> lectureIds) {
        List<LectureJpaEntity> lectureJpaEntities = lectureJpaRepository.findAllById(lectureIds);
        return lectureJpaEntities.stream().map(this::toModel).toList();
    }

    @Override
    public List<Lecture> findAll() {
        List<LectureJpaEntity> lectureJpaEntity = lectureJpaRepository.findAll(Sort.by("id")).stream().toList();
        if (lectureJpaEntity == null || lectureJpaEntity.isEmpty()) {
            return List.of();
        }

        return lectureJpaEntity.stream().map(this::toModel).toList();
    }

    private Lecture toModel(LectureJpaEntity lectureJpaEntity) {
        return Lecture.of(
                lectureJpaEntity.getId(),
                lectureJpaEntity.getTitle(),
                lectureJpaEntity.getContent(),
                lectureJpaEntity.getCapacity()
        );
    }
}
