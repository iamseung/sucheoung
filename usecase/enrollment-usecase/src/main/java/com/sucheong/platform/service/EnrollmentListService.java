package com.sucheong.platform.service;

import com.sucheong.platform.enrollment.model.Enrollment;
import com.sucheong.platform.port.EnrollmentPort;
import com.sucheong.platform.usecase.EnrollmentListUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentListService implements EnrollmentListUsecase {

    private final EnrollmentPort enrollmentPort;

    @Override
    public List<Enrollment> enrollmentListByLectureId(Long lectureId, int pageNumber) {
        return List.of();
    }

    @Override
    public List<Enrollment> enrollmentListByMemberId(Long memberId, int pageNumber) {
        return List.of();
    }
}
