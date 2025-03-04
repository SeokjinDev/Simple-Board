package com.practice.simple_board.api.post.dto.request;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Getter
public class PostCreateDTO {

    private String title;
    private String content;
    private String description;
    private UUID memberUuid;

}
