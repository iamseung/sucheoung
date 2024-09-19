package com.sucheong.platform.enrollment.controller;

import com.sucheong.platform.enrollment.model.EnrollmentRequest;
import com.sucheong.platform.usecase.EnrollmentHistoryUsecase;
import com.sucheong.platform.usecase.RequestEnrollmentUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentHistoryUsecase enrollmentHistoryUsecase;
    private final RequestEnrollmentUsecase requestEnrollmentUsecase;

    @PostMapping
    ResponseEntity<String> enroll(@RequestBody EnrollmentRequest request) {

        if(!enrollmentHistoryUsecase.hasRemainingQuantity(request.getLectureId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("수강 정원이 초과되었습니다.");
        }
        if(!enrollmentHistoryUsecase.isFirstRequestFromUser(request.getMemberId(), request.getLectureId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 수강신청 완료되었습니다.");
        }

        requestEnrollmentUsecase.createEnrollment(request.getMemberId(), request.getLectureId());
        return ResponseEntity.ok().body("수강신청 완료되었습니다.");
    }

    @DeleteMapping
    ResponseEntity<String> enroll(@RequestParam(name = "enrollmentId") Long enrollmentId) {
        requestEnrollmentUsecase.deleteEnrollment(enrollmentId);

        return ResponseEntity.ok().body("수강신청 취소되었습니다.");
    }


}
