package eu.fugiczek.maturita.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.fugiczek.maturita.domain.BlogPost;
import eu.fugiczek.maturita.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByBlogPostOrderByPublishedDateDesc(BlogPost blogPost);
	
}
