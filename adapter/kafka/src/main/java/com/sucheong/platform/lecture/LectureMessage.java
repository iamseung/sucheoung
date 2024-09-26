package com.sucheong.platform.lecture;

import com.sucheong.platform.common.OperationType;
import com.sucheong.platform.lecture.model.Lecture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LectureMessage {

    private Long id;
    private Payload payload;
    private OperationType operationType;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Payload {
        private Long id;
        private String title;
        private String content;
        private int capacity;
    }

    public Lecture toModel() {
        return Lecture.of(
                this.payload.getId(),
                this.payload.getTitle(),
                this.payload.getContent(),
                this.payload.getCapacity()
            );
    }
}
