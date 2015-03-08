package eu.fugiczek.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import eu.fugiczek.entity.User;
import eu.fugiczek.entity.Role;
import eu.fugiczek.repository.RoleRepository;
import eu.fugiczek.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void delete(int id) {
		userRepository.delete(id);
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("USER"));
		user.setRoles(roles);
		userRepository.save(user);
	}

	public User findOne(String username) {
		return userRepository.findByName(username);
	}
	
}
