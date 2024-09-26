package com.sucheong.platform.port;

import com.sucheong.platform.lecture.model.Lecture;

public interface LectureMessageProducePort {
    void sendCreateMessage(Lecture lecture);
    void sendUpdateMessage(Lecture lecture);
    void sendDeleteMessage(Long id);
}
