package com.sucheong.platform.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    private LocalDateTime enrolledAt;
}
