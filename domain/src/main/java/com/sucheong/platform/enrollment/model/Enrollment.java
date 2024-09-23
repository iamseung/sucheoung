package com.sucheong.platform.enrollment.model;

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
    private LocalDateTime createdAt; // 생성일시
    private LocalDateTime modifiedAt; // 수정일시
    private LocalDateTime deletedAt; // 삭제일시

    public Enrollment delete() {
        LocalDateTime now = LocalDateTime.now();
        this.deletedAt = now;
        this.modifiedAt = now;
        return this;
    }

    public Enrollment undelete() {
        this.deletedAt = null;
        this.modifiedAt = LocalDateTime.now();
        return this;
    }

    public static Enrollment generate(Long memberId, Long lectureId) {
        LocalDateTime now = LocalDateTime.now();
        return new Enrollment(null, memberId, lectureId, now, now, null);
    }


    public static Enrollment of(Long id, Long memberId, Long lectureId, LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime deletedAt) {
        return new Enrollment(id, memberId, lectureId, createdAt, modifiedAt, deletedAt);
    }

}
