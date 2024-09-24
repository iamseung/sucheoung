package com.sucheong.platform.usecase;


import com.sucheong.platform.enrollment.model.Enrollment;

import java.util.List;

public interface EnrollmentUsecase {

    Enrollment getById(Long enrollmentId);
    Enrollment save(Long memberId, Long lectureId);
    Enrollment delete(Long enrollmentId);
    List<Enrollment> listByLectureId(Long lectureId);
    List<Enrollment> listByMemberId(Long memberId);
}
