package com.sucheong.platform.lecture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sucheong.platform.CustomObjectMapper;
import com.sucheong.platform.common.OperationType;
import com.sucheong.platform.common.Topic;
import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.port.LectureMessageProducePort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LectureMessageProduceAdapter implements LectureMessageProducePort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    @Override
    public void sendCreateMessage(Lecture lecture) {
        LectureMessage message = this.convertToMessage(lecture.getId(), lecture, OperationType.CREATE);
        this.sendMessage(message);
    }

    @Override
    public void sendUpdateMessage(Lecture lecture) {
        LectureMessage message = this.convertToMessage(lecture.getId(), lecture, OperationType.UPDATE);
        this.sendMessage(message);
    }

    @Override
    public void sendDeleteMessage(Long id) {
        LectureMessage message = this.convertToMessage(id, null, OperationType.DELETE);
        this.sendMessage(message);
    }

    private LectureMessage convertToMessage(Long id, Lecture lecture, OperationType operationType) {
        return new LectureMessage(
                id,
                lecture == null ? null : new LectureMessage.Payload(
                        lecture.getId(),
                        lecture.getTitle(),
                        lecture.getContent(),
                        lecture.getCapacity()
                ),
                operationType
        );
    }

    private void sendMessage(LectureMessage message) {
        try {
            kafkaTemplate.send(Topic.LECTURE, message.getId().toString(), objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
