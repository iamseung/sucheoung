package com.sucheong.platform.usecase;

import org.springframework.stereotype.Component;

public interface EnrollmentHistoryUsecase {

    boolean isFirstRequestFromUser(Long lectureId, Long userId);
    boolean hasRemainingQuantity(Long lectureId);

}
