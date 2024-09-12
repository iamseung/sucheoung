package com.sucheong.platform.lecture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lecture {
    private Long id;
    private String title;
    private String content;
    private int capacity;

    public static Lecture of(Long id,  String title, String content, int capacity) {
        return new Lecture(id, title, content, capacity);
    }
}
