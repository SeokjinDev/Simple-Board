package com.practice.simple_board.api.member.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MemberInfoDTO {

    private String nickname;
    private String email;
    private String password;
    private LocalDateTime createdAt;

}