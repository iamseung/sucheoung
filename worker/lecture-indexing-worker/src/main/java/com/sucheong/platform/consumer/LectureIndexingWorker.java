package com.sucheong.platform.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sucheong.platform.CustomObjectMapper;
import com.sucheong.platform.common.OperationType;
import com.sucheong.platform.common.Topic;
import com.sucheong.platform.lecture.LectureMessage;
import com.sucheong.platform.usecase.LectureIndexingUsecase;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LectureIndexingWorker {

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();
    private final LectureIndexingUsecase lectureIndexingUsecase;

    @KafkaListener(
            topics = {Topic.LECTURE},
            groupId = "index-lecture-consumer-group",
            concurrency = "3"
    )
    public void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        LectureMessage lectureMessage = objectMapper.readValue(message.value(), LectureMessage.class);

        if(lectureMessage.getOperationType() == OperationType.CREATE) {
            this.handleCreate(lectureMessage);
        } else if (lectureMessage.getOperationType() == OperationType.UPDATE) {
            this.handleUpdate(lectureMessage);
        } else if (lectureMessage.getOperationType() == OperationType.DELETE) {
            this.handleDelete(lectureMessage);
        }
    }

    private void handleCreate(LectureMessage lectureMessage) {
        lectureIndexingUsecase.save(lectureMessage.toModel());
    }

    private void handleUpdate(LectureMessage lectureMessage) {
        lectureIndexingUsecase.save(lectureMessage.toModel());
    }

    private void handleDelete(LectureMessage lectureMessage) {
        lectureIndexingUsecase.delete(lectureMessage.getId());
    }

}
