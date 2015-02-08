package eu.fugiczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.fugiczek.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
