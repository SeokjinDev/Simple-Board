package com.practice.simple_board.api.post.service;

import com.practice.simple_board.api.member.repository.MemberRepository;
import com.practice.simple_board.api.post.dto.request.PostCreateDTO;
import com.practice.simple_board.api.post.dto.request.PostUpdateDTO;
import com.practice.simple_board.api.post.dto.response.PostReadDTO;
import com.practice.simple_board.api.post.entity.Post;
import com.practice.simple_board.api.post.repository.PostRepository;
import com.practice.simple_board.common.exception.NotFoundException;
import com.practice.simple_board.common.response.ErrorStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void create(PostCreateDTO postCreateDTO) {

        Post newPost = Post.builder()
                .title(postCreateDTO.getTitle())
                .content(postCreateDTO.getContent())
                .description(postCreateDTO.getDescription())
                .member(memberRepository.findByUuid(postCreateDTO.getMemberUuid()).get())
                .build();

        postRepository.save(newPost);
    }

    @Transactional
    public PostReadDTO read(UUID postUuid) {

        Post post = postRepository.findByUuid(postUuid)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_POST.getMessage())
                );

        return PostReadDTO.builder()
                .postUuid(post.getUuid())
                .authorName(post.getMember().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }

    @Transactional
    public void update(PostUpdateDTO postUpdateDTO) {

        Post post = postRepository.findByUuid(postUpdateDTO.getPostUuid())
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_POST.getMessage())
                );

        Post updatedPost = post.toBuilder()
                .title(postUpdateDTO.getTitle())
                .content(postUpdateDTO.getContent())
                .description(postUpdateDTO.getDescription())
                .build();

        postRepository.save(updatedPost);
    }

    @Transactional
    public void delete(UUID postUuid) {
        Post post = postRepository.findByUuid(postUuid)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_POST.getMessage()));

        postRepository.delete(post);
    }


}
