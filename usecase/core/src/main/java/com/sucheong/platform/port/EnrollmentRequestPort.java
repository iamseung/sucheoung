package com.sucheong.platform.port;

public interface EnrollmentRequestPort {

    void sendCreateMessage(Long memberId, Long lectureId);
    void sendDeleteMessage(Long enrollmentId);
}
