package com.sucheong.platform.service;

import com.sucheong.platform.port.EnrollmentRequestPort;
import com.sucheong.platform.usecase.RequestEnrollmentUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestEnrollmentService implements RequestEnrollmentUsecase {

    private final EnrollmentRequestPort enrollmentRequestPort;

    @Override
    public void createEnrollment(Long memberId, Long lectureId) {
        enrollmentRequestPort.sendCreateMessage(memberId, lectureId);
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) {
        enrollmentRequestPort.sendDeleteMessage(enrollmentId);
    }
}
