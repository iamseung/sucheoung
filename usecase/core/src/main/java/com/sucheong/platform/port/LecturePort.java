package com.sucheong.platform.port;

import com.sucheong.platform.lecture.model.Lecture;

import java.util.List;

public interface LecturePort {

    Lecture findById(Long lectureId);
    List<Lecture> findByIds(List<Long> lectureIds);
    List<Lecture> findAll();
}
