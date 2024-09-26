package com.sucheong.platform.port;

import com.sucheong.platform.lecture.model.Lecture;

import java.util.List;

public interface LectureSearchPort {
    void indexLecture(Lecture lecture);
    void deleteLecture(Long lectureId);
    List<Long> searchLectureIdsByKeyword(String keyword, int pageNumber, int pageSize);
}
