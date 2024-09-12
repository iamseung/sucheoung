package com.sucheong.platform.lecture.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Enrollment {

    private Long id;
    private Long memberId;
    private Long lectureId;
    LocalDateTime enrolledAt;
    
}
