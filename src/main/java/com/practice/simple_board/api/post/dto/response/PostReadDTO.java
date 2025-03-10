package com.practice.simple_board.api.post.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class PostReadDTO {

    private UUID postId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

}
