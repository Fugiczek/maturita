package eu.fugiczek.maturita.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;

@Entity
public class Inquiry {

	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private Integer id;
	
	@Email(message = "Invalid email address!")
	@NotNull
	private String sender;
	
	@Size(min = 3, max = 1000, message = "Text must be 3 - 1000 characters long!")
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(nullable = false, length = 1000)
	private String text;

	private LocalDateTime publishedDate;
	
	private boolean processed;
	
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

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
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
	
	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean proccessed) {
		this.processed = proccessed;
	}
	
}