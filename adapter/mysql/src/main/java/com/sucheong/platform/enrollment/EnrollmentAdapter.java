package com.sucheong.platform.enrollment;

import com.sucheong.platform.enrollment.model.Enrollment;
import com.sucheong.platform.persistence.EnrollmentJpaEntity;
import com.sucheong.platform.port.EnrollmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EnrollmentAdapter implements EnrollmentPort {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    @Override
    public Enrollment save(Enrollment enrollment) {
        EnrollmentJpaEntity enrollmentEntity = enrollmentJpaRepository.save(this.toJpaEntity(enrollment));

        return toModel(enrollmentEntity);
    }

    @Override
    public Enrollment findById(Long enrollmentId) {
        EnrollmentJpaEntity enrollmentEntity = enrollmentJpaRepository.findById(enrollmentId).orElse(null);

        if(enrollmentEntity == null) return null;

        return toModel(enrollmentEntity);
    }

    @Override
    public List<Enrollment> listByLectureId(Long lectureId) {
        List<EnrollmentJpaEntity> enrollmentEntities = enrollmentJpaRepository.findAllByLectureId(lectureId).stream().toList();
        return enrollmentEntities.stream().map(this::toModel).toList();
    }

    @Override
    public List<Enrollment> listByMemberId(Long memberId) {
        List<EnrollmentJpaEntity> enrollmentEntities = enrollmentJpaRepository.findAllByMemberIdAndDeletedAtIsNull(memberId).stream().toList();
        return enrollmentEntities.stream().map(this::toModel).toList();

    }

    private EnrollmentJpaEntity toJpaEntity(Enrollment enrollment) {
        return new EnrollmentJpaEntity(
                enrollment.getId(),
                enrollment.getMemberId(),
                enrollment.getLectureId(),
                null,
                null,
                enrollment.getCreatedAt(),
                enrollment.getModifiedAt(),
                enrollment.getDeletedAt()
        );
    }

    private Enrollment toModel(EnrollmentJpaEntity enrollmentEntity) {
        return Enrollment.of(
                enrollmentEntity.getId(),
                enrollmentEntity.getMemberId(),
                enrollmentEntity.getLectureId(),
                enrollmentEntity.getCreatedAt(),
                enrollmentEntity.getModifiedAt(),
                enrollmentEntity.getDeletedAt()
        );
    }
}
