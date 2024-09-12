package com.sucheong.platform.port;

public interface EnrollmentRequestHistoryPort {

    boolean setHistoryIfNotExists(Long lectureId, Long memberId);

    Long getRequestSequence(Long lectureId);
}
