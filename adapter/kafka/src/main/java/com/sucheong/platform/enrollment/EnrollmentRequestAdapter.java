package com.sucheong.platform.enrollment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sucheong.platform.CustomObjectMapper;
import com.sucheong.platform.common.OperationType;
import com.sucheong.platform.port.EnrollmentRequestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.sucheong.platform.common.Topic.ENROLLMENT_REQUEST;

@Component
@RequiredArgsConstructor
public class EnrollmentRequestAdapter implements EnrollmentRequestPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    @Override
    public void sendCreateMessage(Long memberId, Long lectureId) {
        EnrollmentRequestMessage message = EnrollmentRequestMessage.of(null, memberId, lectureId, OperationType.CREATE);

        try {
            kafkaTemplate.send(ENROLLMENT_REQUEST, message.getMemberId().toString(), objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendDeleteMessage(Long enrollmentId) {
        EnrollmentRequestMessage message = EnrollmentRequestMessage.of(enrollmentId, null, null, OperationType.DELETE);

        try {
            kafkaTemplate.send(ENROLLMENT_REQUEST, message.getEnrollmentId().toString(), objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
