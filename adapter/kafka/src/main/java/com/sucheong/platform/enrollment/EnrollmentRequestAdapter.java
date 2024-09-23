package com.sucheong.platform.enrollment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sucheong.platform.CustomObjectMapper;
import com.sucheong.platform.common.Topic;
import com.sucheong.platform.port.EnrollmentRequestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.sucheong.platform.common.Topic.ENROLLMENT_REQUEST;

@RequiredArgsConstructor
@Component
public class EnrollmentRequestAdapter implements EnrollmentRequestPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    @Override
    public void sendMessage(Long lectureId, Long memberId) {
        EnrollmentRequestMessage message = EnrollmentRequestMessage.from(lectureId, memberId);

        try {
            kafkaTemplate.send(ENROLLMENT_REQUEST, message.getMemberId().toString(), objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
