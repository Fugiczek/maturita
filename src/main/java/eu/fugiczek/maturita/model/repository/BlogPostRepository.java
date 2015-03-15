package eu.fugiczek.maturita.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.fugiczek.maturita.domain.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>{
	
}
