package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Reply {

	@Id		// primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne			// 여러개의 답글은 하나의 게시글에 << 이게 맞는거
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne			// 여러개의 답글은 하나의 유저 << 이게 맞는거
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp			// 자동
	private Timestamp createDate;
}
