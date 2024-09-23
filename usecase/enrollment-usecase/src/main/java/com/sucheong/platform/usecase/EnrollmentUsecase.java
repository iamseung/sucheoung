package com.sucheong.platform.usecase;

import com.sucheong.platform.lecture.model.Enrollment;

public interface EnrollmentUsecase {

    Enrollment getById(Long enrollmentId);
    Enrollment save(Long memberId, Long lectureId);
    Enrollment delete(Long enrollmentId);
}
