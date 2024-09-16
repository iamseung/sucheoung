package com.sucheong.platform.enrollment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrollmentRequestMessage {

    private Long lectureId;
    private Long memberId;

    public static EnrollmentRequestMessage from(Long lectureId, Long memberId) {
        return new EnrollmentRequestMessage(lectureId, memberId);
    }
}
