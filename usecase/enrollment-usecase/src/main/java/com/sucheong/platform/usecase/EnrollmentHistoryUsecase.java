package com.sucheong.platform.usecase;

public interface EnrollmentHistoryUsecase {

    boolean isFirstRequestFromUser(Long memberId, Long LectureId);
    boolean hasRemainingQuantity(Long lectureId);
    boolean deleteRequest(Long memberId, Long lectureId);
}
