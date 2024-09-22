package com.sucheong.platform.lecture.model;

import lombok.Data;

@Data
public class LectureInListDto {
    private final Long id;
    private final String title;
    private final String content;
}
