package eu.fugiczek.maturita.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.fugiczek.maturita.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String username);

}
