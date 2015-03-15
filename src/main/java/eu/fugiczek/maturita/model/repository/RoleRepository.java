package eu.fugiczek.maturita.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.fugiczek.maturita.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String string);

}
