package com.practice.simple_board.api.member.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MemberRegisterDTO {

    private String email;
    private String password;
    private String nickname;

}