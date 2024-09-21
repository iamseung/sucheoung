package com.sucheong.platform.enrollment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnrollmentRequest {
    private Long memberId;
    private Long lectureId;
}
