package com.sucheong.platform.port;

import com.sucheong.platform.lecture.model.Lecture;

import java.util.List;

public interface LectureCachePort {

    void set(Lecture lecture);
    Lecture get(Long lectureId);
    List<Lecture> multiGet(List<Long> lectureIds);
}
