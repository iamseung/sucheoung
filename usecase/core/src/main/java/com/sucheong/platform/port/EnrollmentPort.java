package com.sucheong.platform.port;


import com.sucheong.platform.enrollment.model.Enrollment;

import java.util.List;

public interface EnrollmentPort {

    Enrollment save(Enrollment enrollment);
    Enrollment findById(Long enrollmentId);
    List<Enrollment> listByLectureId(Long lectureId);
    List<Enrollment> listByMemberId(Long memberId);
}
