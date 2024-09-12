package com.sucheong.platform.lecture;

import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.persistence.LectureJpaEntity;
import com.sucheong.platform.port.LecturePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        return Lecture.of(
                lectureJpaEntity.getId(),
                lectureJpaEntity.getTitle(),
                lectureJpaEntity.getContent(),
                lectureJpaEntity.getCapacity()
        );
    }
}
