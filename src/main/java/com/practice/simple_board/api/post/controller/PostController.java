package com.practice.simple_board.api.post.controller;

import com.practice.simple_board.api.post.dto.request.PostCreateDTO;
import com.practice.simple_board.api.post.dto.request.PostUpdateDTO;
import com.practice.simple_board.api.post.dto.response.PostReadDTO;
import com.practice.simple_board.api.post.entity.Post;
import com.practice.simple_board.api.post.service.PostService;
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
@Tag(name = "Post", description = "게시글 관리 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @Operation(
            summary = "게시글 생성 API",
            description = "게시글 생성 API 입니다. 제목, 내용, 설명글, 작성자 uuid를 입력받습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "게시글 생성을 성공하였습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "존재하지 않는 사용자입니다.")
    })
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Void>> post(@RequestBody PostCreateDTO postCreateDTO) {
        postService.create(postCreateDTO);

        return ApiResponse.success(SuccessStatus.SUCCESS_POST_CREATE);
    }

    @Operation(
            summary = "게시글 조회 API",
            description = "게시글 조회 API 입니다. 게시글 uuid, 제목, 내용, 작성자 이름, 생성 일자를 입력받습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "게시글 조회를 성공하였습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "존재하지 않는 게시글입니다.")
    })
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<PostReadDTO>> read(@RequestParam UUID postId) {
        PostReadDTO postReadDTO = postService.read(postId);

        return ApiResponse.success(SuccessStatus.SUCCESS_POST_READ, postReadDTO);
    }

    @Operation(
            summary = "게시글 수정 API",
            description = "게시글 수정 API 입니다. 게시글 uuid, 제목, 내용, 설명글를 입력받습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "게시글 수정을 성공하였습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "존재하지 않는 게시글입니다.")
    })
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Void>> update(@RequestBody PostUpdateDTO postUpdateDTO) {
        postService.update(postUpdateDTO);

        return ApiResponse.success(SuccessStatus.SUCCESS_POST_UPDATE);
    }

    @Operation(
            summary = "게시글 삭제 API",
            description = "게시글 삭제 API 입니다. 게시글 uuid를 입력받습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "게시글 삭제를 성공하였습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "존재하지 않는 게시글입니다.")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> delete(@RequestParam UUID postId) {
        postService.delete(postId);

        return ApiResponse.success(SuccessStatus.SUCCESS_POST_DELETE);
    }
}
