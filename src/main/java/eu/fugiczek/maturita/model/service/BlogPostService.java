package eu.fugiczek.maturita.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.fugiczek.maturita.domain.BlogPost;
import eu.fugiczek.maturita.domain.Comment;
import eu.fugiczek.maturita.domain.User;
import eu.fugiczek.maturita.domain.exception.BlogPostNotFoundException;
import eu.fugiczek.maturita.model.repository.BlogPostRepository;
import eu.fugiczek.maturita.model.repository.CommentRepository;
import eu.fugiczek.maturita.model.repository.UserRepository;

@Service
public class BlogPostService {

	@Autowired
	private BlogPostRepository blogPostRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<BlogPost> findAll(int page) {
		return blogPostRepository.findAll(new PageRequest(page, 10, Direction.DESC, "publishedDate"));
	}
	
	public long count() {
		return blogPostRepository.count();
	}

	public void delete(int id) {
		blogPostRepository.delete(id);
	}

	public BlogPost findOne(int id) {
		return blogPostRepository.findOne(id);
	}

	@Transactional
	public BlogPost findOneWithComments(int id) {
		BlogPost blogPost = findOne(id);
		List<Comment> comments = commentRepository.findByBlogPostOrderByPublishedDateDesc(blogPost);
		blogPost.setComments(comments);
		return blogPost;
	}
	
	public void save(BlogPost blogPost, String name) {
		User user = userRepository.findByName(name);
		blogPost.setUser(user);
		blogPostRepository.save(blogPost);
	}
	
	public void update(int id, BlogPost blogPost) {
		BlogPost original = findOne(id);
		if(original == null) throw new BlogPostNotFoundException(id);
		
		original.setTitle(blogPost.getTitle());
		original.setText(blogPost.getText());
		
		blogPostRepository.save(original);
	}
}
