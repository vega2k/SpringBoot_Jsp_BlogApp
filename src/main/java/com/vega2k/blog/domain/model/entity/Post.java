package com.vega2k.blog.domain.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
public class Post {
	@Id
	@GeneratedValue
	long id;

	@NotNull
	@Size(min=1, max=500)
	@Column(length = 500, nullable = false)
	String subject;
	
	@NotNull
	@Size(min=1, max=100000000)
	@Column(columnDefinition = "TEXT", length=100000000, nullable = false)
	String content;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	LocalDateTime regDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", subject=" + subject + ", content=" + content + ", regDate=" + regDate + "]";
	}

}