package com.sucheong.platform.lecture.converter;

import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.lecture.model.LectureDto;
import com.sucheong.platform.lecture.model.LectureInListDto;

public class LectureConverter {

    public static LectureDto toLectureDto(Lecture lecture, int requestSequentialNumber) {
        return new LectureDto(
                lecture.getId(),
                lecture.getTitle(),
                lecture.getContent(),
                lecture.getCapacity(),
                requestSequentialNumber
        );
    }

    public static LectureInListDto toLectureInListDto(Lecture lecture) {
        return new LectureInListDto(
                lecture.getId(),
                lecture.getTitle(),
                lecture.getContent()
        );
    }
}
