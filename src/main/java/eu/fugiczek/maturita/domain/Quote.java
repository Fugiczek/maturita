package eu.fugiczek.maturita.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
public class Quote {

	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private Integer id;
	
	@Size(min = 3, max = 1000, message = "Text must be 3 - 1000 characters long!")
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(nullable = false, length = 1000)
	private String text;

	@Size(min = 3, max = 100, message = "Author must be 3 - 100 characters long!")
	@Column(nullable = false, length = 100)
	private String author;
	
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}
