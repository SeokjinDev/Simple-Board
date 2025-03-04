package com.practice.simple_board.api.post.repository;

import com.practice.simple_board.api.post.entity.Post;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    Optional<Post> findByUuid(UUID uuid);

    Optional<List<Post>> findAllByMemberUuid(UUID memberUuid);

}