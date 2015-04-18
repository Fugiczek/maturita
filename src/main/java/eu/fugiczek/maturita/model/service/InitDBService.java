package eu.fugiczek.maturita.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import eu.fugiczek.maturita.domain.BlogPost;
import eu.fugiczek.maturita.domain.Comment;
import eu.fugiczek.maturita.domain.Quote;
import eu.fugiczek.maturita.domain.Role;
import eu.fugiczek.maturita.domain.User;
import eu.fugiczek.maturita.model.repository.BlogPostRepository;
import eu.fugiczek.maturita.model.repository.CommentRepository;
import eu.fugiczek.maturita.model.repository.QuoteRepository;
import eu.fugiczek.maturita.model.repository.RoleRepository;
import eu.fugiczek.maturita.model.repository.UserRepository;

@Transactional
@Service
public class InitDBService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private QuoteRepository quoteRepository;
	
	@PostConstruct
	public void init() {
		if(roleRepository.findByName("ADMIN") != null) {
			return;
		}
		
		Role roleUser = new Role();
		roleUser.setName("USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		userAdmin.setEmail("admin@test.org");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		User userUser = new User();
		userUser.setEnabled(true);
		userUser.setName("user");
		userUser.setPassword(encoder.encode("user"));
		userUser.setEmail("user@test.org");
		roles = new ArrayList<Role>();
		roles.add(roleUser);
		userUser.setRoles(roles);
		userRepository.save(userUser);
		
		List<BlogPost> blogPosts = new ArrayList<>();
		BlogPost blogPost;
		Comment comment;
		
		for(int i = 0; i < 21; i++) {
			blogPost = new BlogPost();
			blogPost.setTitle("Test post " + i);
			blogPost.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
			blogPost.setUser(userAdmin);
			
			blogPostRepository.save(blogPost);

			blogPosts.add(blogPost);
		}
		
		for(BlogPost post : blogPosts) {
			for(int j = 0; j < 3; j++) {
				comment = new Comment();
				comment.setBlogPost(post);
				comment.setUser(userAdmin);
				comment.setText("Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.");
				
				commentRepository.save(comment);
			}
		}
		
		Quote quote;
		
		for(int i = 0; i < 5; i++) {
			quote = new Quote();
			
			quote.setAuthor("Test");
			quote.setText(i + ". quote");
			
			quoteRepository.save(quote);
		}
		
	}
	
}
