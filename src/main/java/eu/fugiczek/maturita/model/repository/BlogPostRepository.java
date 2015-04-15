package eu.fugiczek.maturita.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import eu.fugiczek.maturita.domain.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>{
	
	@Modifying
	@Query("update BlogPost b set b.commentable = ?1")
	void enableComments(boolean enable);
	
}
