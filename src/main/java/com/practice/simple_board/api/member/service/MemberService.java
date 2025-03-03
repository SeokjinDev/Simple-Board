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

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void register(MemberRegisterDTO memberRegisterDTO) {
        Member newMember = Member.builder()
                .nickname(memberRegisterDTO.getNickname())
                .email(memberRegisterDTO.getEmail())
                .password(memberRegisterDTO.getPassword())
                .build();

        validateDuplicateMember(newMember);
        memberRepository.save(newMember);
    }

    @Transactional
    public MemberInfoDTO info(String email) {

        Member member = validateAndReturnEmail(email);

        return MemberInfoDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
    }

    @Transactional
    public void update(MemberUpdateDTO memberUpdateDTO) {
        Member member = validateAndReturnEmail(memberUpdateDTO.getOldEmail()).toBuilder()
                .nickname(memberUpdateDTO.getNickname())
                .email(memberUpdateDTO.getEmail())
                .password(memberUpdateDTO.getPassword())
                .build();

        memberRepository.save(member);
    }

    @Transactional
    public void quit(String email) {
        Member member = validateAndReturnEmail(email);

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

    public Member validateAndReturnEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }
}