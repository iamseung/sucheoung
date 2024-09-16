package com.sucheong.platform.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "enrollment")
public class EnrollmentJpaEntity extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberJpaEntity member;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private LectureJpaEntity lecture;
}
