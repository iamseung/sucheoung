package com.sucheong.platform.service;

import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.port.EnrollmentRequestHistoryPort;
import com.sucheong.platform.port.LectureCachePort;
import com.sucheong.platform.port.LecturePort;
import com.sucheong.platform.usecase.EnrollmentHistoryUsecase;
import com.sucheong.platform.usecase.LectureUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EnrollmentHistoryService implements EnrollmentHistoryUsecase {

    private final EnrollmentRequestHistoryPort enrollmentRequestHistoryPort;
    private final LectureUsecase lectureUsecase;


    @Override
    public boolean isFirstRequestFromUser(Long memberId, Long lectureId) {
        return enrollmentRequestHistoryPort.setHistoryIfNotExists(memberId, lectureId);
    }

    @Override
    public boolean hasRemainingQuantity(Long lectureId) {
        Lecture lecture = lectureUsecase.getById(lectureId);
        return enrollmentRequestHistoryPort.hasRemainingQuantity(lectureId, lecture.getCapacity());
    }

    @Override
    public boolean deleteRequest(Long memberId, Long lectureId) {
        return enrollmentRequestHistoryPort.deleteRequest(memberId, lectureId);
    }

    @Override
    public int getRequestSequentialNumber(Long lectureId) {
        return enrollmentRequestHistoryPort.getRequestSequentialNumber(lectureId);
    }

    @Override
    public List<Long> getEnrolledLectureIdsByMemberId(Long memberId) {
        return enrollmentRequestHistoryPort.getEnrolledLecturesByMemberId(memberId);
    }

    @Override
    public List<Long> getEnrolledMemberIdsByLectureId(Long lectureId)  {
        return enrollmentRequestHistoryPort.getEnrolledUsersByLectureId(lectureId);
    }

}
