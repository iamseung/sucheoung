package com.sucheong.platform.usecase;

public interface RequestEnrollmentUsecase {
    void queue(Long lectureId, Long userId);
}
