package com.sucheong.platform.service;

import com.sucheong.platform.enrollment.model.Enrollment;
import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.port.EnrollmentPort;
import com.sucheong.platform.port.LectureCachePort;
import com.sucheong.platform.port.LecturePort;
import com.sucheong.platform.usecase.LectureUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class LectureService implements LectureUsecase {

    private final LecturePort lecturePort;
    private final LectureCachePort lectureCachePort;
    private final EnrollmentPort enrollmentPort;


    @Override
    public List<Lecture> getAll() {
        return lecturePort.findAll();
    }

    @Override
    public Lecture getById(Long lectureId) {
        Lecture lectureCache = lectureCachePort.get(lectureId);

        if(lectureCache != null) {
            return lectureCache;
        } else {
            Lecture lecture = lecturePort.findById(lectureId);

            if(lecture == null) {
                throw new RuntimeException("Lecture does not exist.");
            }

            lectureCachePort.set(lecture);
            return lecture;
        }
    }

    @Override
    public List<Lecture> lectureListByIds(List<Long> lectureIds) {

        if(lectureIds == null || lectureIds.isEmpty()) {
            return List.of();
        }

        List<Lecture> lectures = new ArrayList<>();
        lectures.addAll(lectureCachePort.multiGet(lectureIds));

        List<Long> missingLectureIds = lectureIds.stream()
                .filter(lectureId -> lectures.stream().noneMatch(cachedLecture -> cachedLecture.getId().equals(lectureIds)))
                .toList();
        List<Lecture> missingLectures = lecturePort.findByIds(missingLectureIds);
        lectures.addAll(missingLectures);

        if(lectures != null || !lectures.isEmpty()) {
            lectures.sort(Comparator.comparingLong(Lecture::getId));
        }

        return lectures;
    }

    @Override
    public List<Lecture> lectureListByMemberId(Long memberId) {

        List<Lecture> lectures = new ArrayList<>();
        List<Enrollment> enrollments = enrollmentPort.listByMemberId(memberId);

        if(enrollments == null || enrollments.isEmpty()) return List.of();

        List<Long> lectureIds = enrollments.stream().map(Enrollment::getLectureId).toList();
        lectures.addAll(lectureCachePort.multiGet(lectureIds));
        List<Long> missingLectureIds = lectureIds.stream().filter(lectureId -> lectures.stream().noneMatch(cachedLecture -> cachedLecture.getId().equals(lectureId)))
                .toList();
        lectures.addAll(lecturePort.findByIds(missingLectureIds));

        if(lectures != null || !lectures.isEmpty()) {
            lectures.sort(Comparator.comparingLong(Lecture::getId));
        }


        return lectures;
    }
}
