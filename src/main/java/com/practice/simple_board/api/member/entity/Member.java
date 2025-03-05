package com.practice.simple_board.api.member.entity;

import com.practice.simple_board.api.post.entity.Post;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.*;

@Getter
@Entity
@Builder(toBuilder = true)
@Table(name = "member")
@AllArgsConstructor()
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID memberId;

    @Column(unique = true)
    private String email;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

    public Member authorizeAdmin() {
        return this.toBuilder()
                .role(Role.ADMIN)
                .build();
    }

    public Member updateNickname(String newNickname) {
        return this.toBuilder()
                .nickname(newNickname)
                .build();
    }
}