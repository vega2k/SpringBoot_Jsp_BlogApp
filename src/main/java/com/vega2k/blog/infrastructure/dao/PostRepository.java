package com.vega2k.blog.infrastructure.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vega2k.blog.domain.model.entity.Post;

public interface PostRepository extends JpaRepository <Post, Long> {
	Post findBySubject(String subject);
}