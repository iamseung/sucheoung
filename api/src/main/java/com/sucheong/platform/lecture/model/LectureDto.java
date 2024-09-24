package com.sucheong.platform.lecture.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class LectureDto {
    private final Long id;
    private final String title;
    private final String content;
    private final int capacity;
    private final int enrolledCount;
}
