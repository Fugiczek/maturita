package eu.fugiczek.maturita.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.fugiczek.maturita.domain.BlogPost;
import eu.fugiczek.maturita.domain.Comment;
import eu.fugiczek.maturita.domain.User;
import eu.fugiczek.maturita.domain.exception.BlogPostNotFoundException;
import eu.fugiczek.maturita.model.repository.BlogPostRepository;
import eu.fugiczek.maturita.model.repository.CommentRepository;
import eu.fugiczek.maturita.model.repository.UserRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private BlogPostRepository blogPostRepository;

	@Autowired
	private UserRepository userRepository;

	public void save(Comment comment, String name, int id) {
		BlogPost blogPost = blogPostRepository.findOne(id);
		if (blogPost == null) {
			throw new BlogPostNotFoundException(id);
		}
		User user = userRepository.findByName(name);
		comment.setBlogPost(blogPost);
		comment.setUser(user);
		
		/*
		 * it fixes bug
		 * when InitDBService is filling up comments
		 * Comment in the controller has the ID of the BlogPost
		 */
		comment.setId(null); 
		
		commentRepository.save(comment);
	}

}
