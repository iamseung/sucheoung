package com.sucheong.platform.service;

import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.port.LectureSearchPort;
import com.sucheong.platform.usecase.LectureIndexingUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureIndexingService implements LectureIndexingUsecase {

    private final LectureSearchPort lectureSearchPort;

    @Override
    public void save(Lecture lecture) {
        lectureSearchPort.indexLecture(lecture);
    }

    @Override
    public void delete(Long lectureId) {
        lectureSearchPort.deleteLecture(lectureId);
    }
}
