package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder		// 빌더 패턴!!!
@Entity			// Entity는 제일 가까이 있는게 좋음.
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob			// 대용량 데이터
	private String content;			// 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 된다.
	
	@ColumnDefault("0")			// 얘는 int니까 ''없이!
	private int count;			// 조회수
	
	@ManyToOne(fetch = FetchType.EAGER)		// Many = Board, User = One.	한명의 유저는 여러개의 게시글을 쓸 수 있다라는 의미.
	@JoinColumn(name="userId")
	private User user;		// DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)		// mappedBy가 적혀있으면 연관관계의 주인이 아니다. 즉, 난 FK가 아니에요 라는 의미. DB에 칼럼을 만들지 마라!!이거임
	private List<Reply> reply;			// 얘는 FK가 필요가없어. 즉, @JoinColumn을 적을 필요가 없다는거. 가져올 값이 1개가 아니잖아!
	
	@CreationTimestamp		// 자동
	private Timestamp createDate;
}
