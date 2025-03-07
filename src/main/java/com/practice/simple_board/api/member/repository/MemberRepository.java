package com.practice.simple_board.api.member.repository;

import com.practice.simple_board.api.member.entity.Member;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    @NonNull
    Optional<Member> findByMemberId(@NonNull UUID memberId);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}