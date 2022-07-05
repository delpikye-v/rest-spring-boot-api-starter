package com.dp.spring.template.repositories.role;

import com.dp.spring.template.models.role.Role;
import com.dp.spring.template.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(Constants.RoleSecurity name);
}
