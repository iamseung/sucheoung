package com.sucheong.platform.port;

import java.util.List;

public interface EnrollmentRequestHistoryPort {

    boolean setHistoryIfNotExists(Long lectureId, Long memberId);

    boolean hasRemainingQuantity(Long lectureId, int capacity);

    boolean deleteRequest(Long memberId, Long lectureId);

    int getRequestSequentialNumber(Long lectureId);

    List<Long> getEnrolledLecturesByMemberId(Long memberId);
    List<Long> getEnrolledUsersByLectureId(Long lectureId);
}
