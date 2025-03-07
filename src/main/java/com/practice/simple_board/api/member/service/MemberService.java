package com.practice.simple_board.api.member.service;

import com.practice.simple_board.api.member.dto.request.MemberRegisterDTO;
import com.practice.simple_board.api.member.dto.request.MemberUpdateDTO;
import com.practice.simple_board.api.member.dto.response.MemberInfoDTO;
import com.practice.simple_board.api.member.entity.Member;
import com.practice.simple_board.api.member.repository.MemberRepository;
import com.practice.simple_board.common.exception.NotFoundException;
import com.practice.simple_board.common.response.ErrorStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void register(MemberRegisterDTO memberRegisterDTO) {
        Member newMember = Member.builder()
                .email(memberRegisterDTO.getEmail())
                .password(memberRegisterDTO.getPassword())
                .nickname(memberRegisterDTO.getNickname())
                .build();

        validateDuplicateMember(newMember);
        memberRepository.save(newMember);
    }

    @Transactional
    public MemberInfoDTO info(UUID memberId) {

        Member member = validateAndReturnByUUID(memberId);
        return MemberInfoDTO.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .build();
    }

    @Transactional
    public void update(MemberUpdateDTO memberUpdateDTO) {

        Member member = validateAndReturnByUUID(memberUpdateDTO.getMemberId());

        Member updatedMember = member.toBuilder()
                .email(memberUpdateDTO.getEmail())
                .password(memberUpdateDTO.getPassword())
                .nickname(memberUpdateDTO.getNickname())
                .build();

        memberRepository.save(updatedMember);
    }

    @Transactional
    public void quit(UUID memberId) {
        Member member = validateAndReturnByUUID(memberId);

        memberRepository.delete(member);
    }

    private void validateDuplicateMember(Member member) {
        validateDuplicateNickname(member.getNickname());
        validateDuplicateEmail(member.getEmail());
    }

    private void validateDuplicateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException(ErrorStatus.DUPLICATE_NICKNAME.getMessage() + nickname);
        }
    }

    private void validateDuplicateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(ErrorStatus.DUPLICATE_EMAIL.getMessage() + email);
        }
    }

    public Member validateAndReturnByUUID(UUID memberId) {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }
}