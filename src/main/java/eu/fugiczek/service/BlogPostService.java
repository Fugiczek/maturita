package eu.fugiczek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import eu.fugiczek.entity.BlogPost;
import eu.fugiczek.repository.BlogPostRepository;

@Service
public class BlogPostService {

	@Autowired
	private BlogPostRepository blogPostRepository;
	
	public List<BlogPost> findAll(int page) {
		return blogPostRepository.findAll(new PageRequest(page, 10, Direction.DESC, "publishedDate")).getContent();
	}
	
	public long count() {
		return blogPostRepository.count();
	}

	public void delete(int id) {
		blogPostRepository.delete(id);
	}
	
}
