package com.sucheong.platform.usecase;

import com.sucheong.platform.lecture.model.Lecture;

public interface LectureIndexingUsecase {

    void save(Lecture lecture);
    void delete(Long lectureId);
}
