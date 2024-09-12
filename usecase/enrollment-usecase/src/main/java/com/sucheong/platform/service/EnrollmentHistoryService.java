package com.sucheong.platform.service;

import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.port.EnrollmentRequestHistoryPort;
import com.sucheong.platform.port.LectureCachePort;
import com.sucheong.platform.port.LecturePort;
import com.sucheong.platform.usecase.EnrollmentHistoryUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnrollmentHistoryService implements EnrollmentHistoryUsecase {

    private final EnrollmentRequestHistoryPort enrollmentRequestHistoryPort;
    private final LecturePort lecturePort;
    private final LectureCachePort lectureCachePort;


    @Override
    public boolean isFirstRequestFromUser(Long lectureId, Long userId) {
        return enrollmentRequestHistoryPort.setHistoryIfNotExists(lectureId, userId);
    }

    @Override
    public boolean hasRemainingQuantity(Long lectureId) {
        Lecture lecture = this.getLectureById(lectureId);
        return enrollmentRequestHistoryPort.getRequestSequence(lectureId) <= lecture.getCapacity();
    }

    private Lecture getLectureById(Long lectureId) {
        Lecture lectureCache = lectureCachePort.get(lectureId);

        if(lectureCache != null) {
            return lectureCache;
        } else {
            Lecture lecture = lecturePort.findById(lectureId);

            if(lecture == null) {
                throw new RuntimeException("Lecture does not exist.");
            }

            lectureCachePort.set(lecture);
            return lecture;
        }
    }

}
