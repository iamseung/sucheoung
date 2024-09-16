package com.sucheong.platform.port;

import com.sucheong.platform.lecture.model.Lecture;

public interface LecturePort {

    Lecture findById(Long lectureId);
}
