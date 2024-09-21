package com.sucheong.platform.port;

public interface EnrollmentRequestHistoryPort {

    boolean setHistoryIfNotExists(Long lectureId, Long memberId);

    boolean hasRemainingQuantity(Long lectureId, int capacity);

    boolean deleteRequest(Long memberId, Long lectureId);
}
