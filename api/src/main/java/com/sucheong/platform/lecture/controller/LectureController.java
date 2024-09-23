package com.sucheong.platform.lecture.controller;

import com.sucheong.platform.lecture.LectureCacheAdapter;
import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.lecture.model.LectureDto;
import com.sucheong.platform.usecase.EnrollmentHistoryUsecase;
import com.sucheong.platform.usecase.LectureUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sucheong.platform.lecture.converter.LectureConverter.toLectureDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lecture")
public class LectureController {

    private final LectureUsecase lectureUsecase;
    private final EnrollmentHistoryUsecase enrollmentHistoryUsecase;

    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureDto> getLecture(@PathVariable Long lectureId) {

        Lecture lecture = lectureUsecase.getById(lectureId);
        int requestSequentialNumber = enrollmentHistoryUsecase.getRequestSequentialNumber(lectureId);

        if(lecture == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(toLectureDto(lecture, requestSequentialNumber));
    }

}
