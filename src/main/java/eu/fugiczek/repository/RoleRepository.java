package eu.fugiczek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.fugiczek.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String string);

}
