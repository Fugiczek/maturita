package eu.fugiczek.maturita.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
public class BlogPost {

	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private Integer id;

	@Size(min = 1, message = "Title must be at least 1 character!")
	@Column(nullable = false, length = 1000)
	private String title;

	private LocalDateTime publishedDate;

	@Size(min = 50, message = "Text must be at least 50 characters!")
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(nullable = false, length = Integer.MAX_VALUE)
	private String text;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "blogPost", cascade = CascadeType.REMOVE)
	private List<Comment> comments;

	private boolean commentable;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getPublishedDate() {
		return publishedDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public boolean isCommentable() {
		return commentable;
	}

	public void setCommentable(boolean commentable) {
		this.commentable = commentable;
	}
}