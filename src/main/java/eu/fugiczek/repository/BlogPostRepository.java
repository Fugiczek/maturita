package eu.fugiczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.fugiczek.entity.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>{

}
