package com.practice.simple_board.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessStatus {

    // 200 OK
    SUCCESS_MEMBER_REGISTERED(HttpStatus.OK, "회원 가입을 성공하였습니다."),
    SUCCESS_MEMBER_INFO_LOADED(HttpStatus.OK, "회원 정보 조회를 성공하였습니다."),
    SUCCESS_MEMBER_UPDATED(HttpStatus.OK, "회원 정보 수정을 성공하였습니다."),
    SUCCESS_MEMBER_QUITED(HttpStatus.OK, "회원 탈퇴를 성공하였습니다."),

    SUCCESS_POST_CREATE(HttpStatus.OK, "게시글 생성을 성공하였습니다."),
    SUCCESS_POST_READ(HttpStatus.OK, "게시글 조회를 성공하였습니다."),
    SUCCESS_POST_UPDATE(HttpStatus.OK, "게시글 수정을 성공하였습니다."),
    SUCCESS_POST_DELETE(HttpStatus.OK, "게시글 삭제를 성공하였습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getStatusCode() {
        return this.httpStatus.value();
    }
}
