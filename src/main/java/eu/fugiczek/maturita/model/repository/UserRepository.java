package eu.fugiczek.maturita.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import eu.fugiczek.maturita.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String username);
	
	User findByEmail(String email);

	@Modifying
	@Query("update User u set u.enabled = ?1 where u.id = ?2")
	void enableUser(boolean enable, int id);
	
}
