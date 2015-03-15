package eu.fugiczek.maturita.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.fugiczek.maturita.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
