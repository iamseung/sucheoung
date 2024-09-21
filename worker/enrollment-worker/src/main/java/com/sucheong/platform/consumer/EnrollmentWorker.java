package com.sucheong.platform.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sucheong.platform.CustomObjectMapper;
import com.sucheong.platform.common.OperationType;
import com.sucheong.platform.common.Topic;
import com.sucheong.platform.enrollment.EnrollmentRequestMessage;
import com.sucheong.platform.usecase.EnrollmentUsecase;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EnrollmentWorker {

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();
    private final EnrollmentUsecase enrollmentUsecase;

    @KafkaListener(
            topics = {Topic.ENROLLMENT_REQUEST},
            groupId = "enrollment-request",
            concurrency = "3"
    )
    public void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        EnrollmentRequestMessage enrollmentRequestMessage = objectMapper.readValue(message.value(), EnrollmentRequestMessage.class);

        if(enrollmentRequestMessage.getOperationType() == OperationType.CREATE) {
            this.handleCreate(enrollmentRequestMessage);
        } else if (enrollmentRequestMessage.getOperationType() == OperationType.DELETE) {
            this.handleDelete(enrollmentRequestMessage);
        }
    }

    private void handleCreate(EnrollmentRequestMessage enrollmentRequestMessage) {
        enrollmentUsecase.save(enrollmentRequestMessage.getMemberId(), enrollmentRequestMessage.getLectureId());
    }

    private void handleDelete(EnrollmentRequestMessage enrollmentRequestMessage) {
        enrollmentUsecase.delete(enrollmentRequestMessage.getEnrollmentId());
    }

}
