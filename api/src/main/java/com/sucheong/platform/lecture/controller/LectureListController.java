package com.sucheong.platform.lecture.controller;

import com.sucheong.platform.lecture.converter.LectureConverter;
import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.lecture.model.LectureInListDto;
import com.sucheong.platform.usecase.LectureUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lectures")
public class LectureListController {

    private final LectureUsecase lectureUsecase;

    @GetMapping
    ResponseEntity<List<LectureInListDto>> listLectures() {
        List<Lecture> lectures = lectureUsecase.getAll();
        return ResponseEntity.ok().body(lectures.stream().map(LectureConverter::toLectureInListDto).toList());
    }

    @GetMapping("/enrolled-list/{memberId}")
    ResponseEntity<List<LectureInListDto>> listEnrolledLectures(@PathVariable Long memberId) {

        List<Lecture> lectures = lectureUsecase.lectureListByMemberId(memberId);
        return ResponseEntity.ok().body(lectures.stream().map(LectureConverter::toLectureInListDto).toList());
    }

}
