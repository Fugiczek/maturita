package eu.fugiczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.fugiczek.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String username);

}
