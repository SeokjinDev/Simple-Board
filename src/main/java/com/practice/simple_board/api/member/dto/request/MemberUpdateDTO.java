package com.practice.simple_board.api.member.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MemberUpdateDTO {
    private final String oldEmail; // 추후 토큰 도입하면서 제거할 예정

    private final String nickname;
    private final String email;
    private final String password;
}
