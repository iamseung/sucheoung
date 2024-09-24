package com.sucheong.platform.service;

import com.sucheong.platform.enrollment.model.Enrollment;
import com.sucheong.platform.port.EnrollmentPort;
import com.sucheong.platform.usecase.EnrollmentHistoryUsecase;
import com.sucheong.platform.usecase.EnrollmentUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Enrollment> listByLectureId(Long lectureId) {
        List<Enrollment> enrollments = enrollmentPort.listByLectureId(lectureId);
        if(enrollments == null || enrollments.isEmpty()) return List.of();

        return enrollments;
    }

    @Override
    public List<Enrollment> listByMemberId(Long memberId) {
        List<Enrollment> enrollments = enrollmentPort.listByMemberId(memberId);
        if(enrollments == null || enrollments.isEmpty()) return List.of();

        return enrollments;
    }
}
