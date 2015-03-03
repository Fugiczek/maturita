package eu.fugiczek.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import eu.fugiczek.entity.BlogPost;
import eu.fugiczek.entity.Role;
import eu.fugiczek.entity.User;
import eu.fugiczek.repository.BlogPostRepository;
import eu.fugiczek.repository.RoleRepository;
import eu.fugiczek.repository.UserRepository;

@Transactional
@Service
public class InitDBService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	
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
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		User userUser = new User();
		userUser.setEnabled(true);
		userUser.setName("User");
		userUser.setPassword(encoder.encode("user"));
		userUser.setEmail("parek@satani.org");
		roles = new ArrayList<Role>();
		roles.add(roleUser);
		userUser.setRoles(roles);
		userRepository.save(userUser);
		
		BlogPost blogPost;
		
		for(int i = 0; i < 21; i++) {
			blogPost = new BlogPost();
			blogPost.setTitle("Zkouska clanku " + i);
			blogPost.setPublishedDate(new Date(System.currentTimeMillis() + i * 10000));
			blogPost.setText("Bla bla bla " + i);
			blogPost.setUser(userAdmin);
			blogPostRepository.save(blogPost);
		}
		
	}
	
}
