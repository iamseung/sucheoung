package com.sucheong.platform.enrollment;

import com.sucheong.platform.common.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrollmentRequestMessage {

    private Long enrollmentId;
    private Long memberId;
    private Long lectureId;
    private OperationType operationType;

    public static EnrollmentRequestMessage of(Long enrollmentId, Long memberId, Long lectureId, OperationType operationType) {
        return new EnrollmentRequestMessage(enrollmentId, memberId, lectureId,operationType);
    }
}
