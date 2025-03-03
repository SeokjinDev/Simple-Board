package com.practice.simple_board.api.member.dto.request;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Getter
public class MemberUpdateDTO {

    private final UUID uuid;
    private final String email;
    private final String password;
    private final String nickname;

}
