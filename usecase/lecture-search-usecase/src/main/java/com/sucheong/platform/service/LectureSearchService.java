package com.sucheong.platform.service;

import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.port.LectureSearchPort;
import com.sucheong.platform.usecase.LectureSearchUsecase;
import com.sucheong.platform.usecase.LectureUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureSearchService implements LectureSearchUsecase {

    private static final int PAGE_SIZE = 20;

    private final LectureSearchPort lectureSearchPort;
    private final LectureUsecase lectureUsecase;

    @Override
    public List<Lecture> getSearchResultByKeyword(String keyword, int pageNumber) {
        List<Long> lectureIds = lectureSearchPort.searchLectureIdsByKeyword(keyword, pageNumber, PAGE_SIZE);
        return lectureUsecase.lectureListByIds(lectureIds);
    }
}
