package com.sucheong.platform.persistence;

import jakarta.persistence.*;

@Entity(name = "member")
public class MemberJpaEntity extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String code;
}
