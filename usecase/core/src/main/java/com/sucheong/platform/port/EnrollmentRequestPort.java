package com.sucheong.platform.port;

public interface EnrollmentRequestPort {

    void sendMessage(Long lectureId, Long memberId);
}
