package com.sucheong.platform.service;

import com.sucheong.platform.lecture.model.Enrollment;
import com.sucheong.platform.port.EnrollmentPort;
import com.sucheong.platform.usecase.EnrollmentHistoryUsecase;
import com.sucheong.platform.usecase.EnrollmentUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EnrollmentService implements EnrollmentUsecase {

    private final EnrollmentPort enrollmentPort;
    private final EnrollmentHistoryUsecase enrollmentHistoryUsecase;

    @Override
    public Enrollment getById(Long enrollmentId) {
        // TODO redis cache 처리할 부분
        return enrollmentPort.findById(enrollmentId);
    }

    @Transactional
    @Override
    public Enrollment save(Long memberId, Long lectureId) {
        Enrollment enrollment = Enrollment.generate(memberId,lectureId);
        return enrollmentPort.save(enrollment);
    }

    @Transactional
    @Override
    public Enrollment delete(Long enrollmentId) {
        Enrollment enrollment = enrollmentPort.findById(enrollmentId);

        if(enrollment == null) return null;

        enrollment = enrollment.delete();
        Enrollment deletedEnrollment = enrollmentPort.save(enrollment);
        enrollmentHistoryUsecase.deleteRequest(enrollment.getMemberId(), enrollment.getLectureId());

        return deletedEnrollment;
    }
}
