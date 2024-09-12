package com.sucheong.platform.port;

public interface EnrollmentRequestHistoryPort {

    boolean setHistoryIfNotExists(Long lectureId, Long userId);

    Long getRequestSequence(Long lectureId);
}
