package com.sucheong.platform.usecase;

import com.sucheong.platform.lecture.model.Lecture;

import java.util.List;

public interface LectureUsecase {

    List<Lecture> getAll();

    Lecture getById(Long lectureId);

    List<Lecture> lectureListByIds(List<Long> lectureIds);

    List<Lecture> lectureListByMemberId(Long memberId);
}
