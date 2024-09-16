package com.sucheong.platform.port;

import com.sucheong.platform.lecture.model.Lecture;

public interface LectureCachePort {

    void set(Lecture lecture);
    Lecture get(Long lectureId);
}
