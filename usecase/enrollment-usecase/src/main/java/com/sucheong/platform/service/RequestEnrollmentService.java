package com.sucheong.platform.service;

import com.sucheong.platform.usecase.RequestEnrollmentUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestEnrollmentService implements RequestEnrollmentUsecase {

    @Override
    public void queue(Long lectureId, Long userId) {

    }
}
