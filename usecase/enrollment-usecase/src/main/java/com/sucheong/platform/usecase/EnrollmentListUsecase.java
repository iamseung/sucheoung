package com.sucheong.platform.usecase;

import com.sucheong.platform.enrollment.model.Enrollment;

import java.util.List;

public interface EnrollmentListUsecase {
    List<Enrollment> enrollmentListByLectureId(Long lectureId, int pageNumber);

    List<Enrollment> enrollmentListByMemberId(Long memberId, int pageNumber);
}
