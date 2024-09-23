package com.sucheong.platform.usecase;

public interface RequestEnrollmentUsecase {
    void createEnrollment(Long memberId, Long lectureId);
    void deleteEnrollment(Long enrollmentId);
}
