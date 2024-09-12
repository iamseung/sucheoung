package com.sucheong.platform.usecase;

public interface EnrollmentHistoryUsecase {

    boolean isFirstRequestFromUser(Long lectureId, Long memberId);
    boolean hasRemainingQuantity(Long lectureId);

}
