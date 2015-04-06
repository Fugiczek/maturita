package eu.fugiczek.maturita.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 3, max = 1000, message = "Text must be 3 - 1000 characters long!")
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(length = 1000)
	private String text;

	private LocalDateTime publishedDate;

	@ManyToOne
	@JoinColumn(name = "blogPost_id")
	private BlogPost blogPost;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@PrePersist
	void publishedDate() {
		publishedDate = LocalDateTime.now();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getPublishedDate() {
		return publishedDate;
	}

	public BlogPost getBlogPost() {
		return blogPost;
	}

	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
