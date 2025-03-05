package com.practice.simple_board.api.member.controller;

import com.practice.simple_board.api.member.dto.request.MemberRegisterDTO;
import com.practice.simple_board.api.member.dto.request.MemberUpdateDTO;
import com.practice.simple_board.api.member.dto.response.MemberInfoDTO;
import com.practice.simple_board.api.member.service.MemberService;
import com.practice.simple_board.common.response.ApiResponse;
import com.practice.simple_board.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@Tag(name = "Member", description = "회원 관리 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "회원 가입 API",
            description = "회원 가입 API 입니다. 닉네임, 이메일, 패스워드를 입력받습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "회원 가입을 성공하였습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400", description = "이미 존재하는 닉네임입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400", description = "이미 존재하는 이메일입니다.")
    })
    @PostMapping("register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody MemberRegisterDTO memberRegisterDTO) {
        memberService.register(memberRegisterDTO);

        return ApiResponse.success(SuccessStatus.SUCCESS_MEMBER_REGISTERED);
    }

    @Operation(
            summary = "회원 정보 조회 API",
            description = "회원 정보 조회 API 입니다. 이메일을 입력받아, 회원 정보를 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "회원 정보 조회를 성공하였습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "존재하지 않는 사용자입니다.")
    })
    @GetMapping("info")
    public ResponseEntity<ApiResponse<MemberInfoDTO>> info(@RequestParam("uuid") UUID memberId) {
        MemberInfoDTO memberInfoDTO = memberService.info(memberId);

        return ApiResponse.success(SuccessStatus.SUCCESS_MEMBER_REGISTERED, memberInfoDTO);
    }

    @Operation(
            summary = "회원 정보 수정 API",
            description = "회원 정보 수정 API 입니다. 닉네임, 이메일, 비밀번호를 입력받습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "회원 정보 수정을 성공하였습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "존재하지 않는 사용자입니다.")
    })
    @PutMapping("update")
    public ResponseEntity<ApiResponse<Void>> update(@RequestBody MemberUpdateDTO memberUpdateDTO) {
        memberService.update(memberUpdateDTO);

        return ApiResponse.success(SuccessStatus.SUCCESS_MEMBER_UPDATED);
    }

    @Operation(
            summary = "회원 탈퇴 API",
            description = "회원 탈퇴 API 입니다. 이메일을 입력받습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "회원 탈퇴를 성공하였습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "존재하지 않는 사용자입니다.")
    })
    @DeleteMapping("quit")
    public ResponseEntity<ApiResponse<Void>> quit(@RequestParam("uuid") UUID memberId) {

        memberService.quit(memberId);

        return ApiResponse.success(SuccessStatus.SUCCESS_MEMBER_QUITED);
    }
}