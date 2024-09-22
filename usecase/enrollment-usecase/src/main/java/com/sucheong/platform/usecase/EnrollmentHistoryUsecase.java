package com.sucheong.platform.usecase;

import java.util.List;

public interface EnrollmentHistoryUsecase {

    boolean isFirstRequestFromUser(Long memberId, Long LectureId);
    boolean hasRemainingQuantity(Long lectureId);
    boolean deleteRequest(Long memberId, Long lectureId);
    int getRequestSequentialNumber(Long lectureId);
    List<Long> getEnrolledLectureIdsByMemberId(Long memberId);
    List<Long> getEnrolledMemberIdsByLectureId(Long lectureId);
}
