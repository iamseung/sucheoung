package com.sucheong.platform.usecase;

import com.sucheong.platform.lecture.model.Lecture;

import java.util.List;

public interface LectureSearchUsecase {
    List<Lecture> getSearchResultByKeyword(String keyword, int pageNumber);
}
