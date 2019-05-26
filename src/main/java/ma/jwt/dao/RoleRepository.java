package ma.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.jwt.entities.AppRoles;
import ma.jwt.entities.AppUSer;

public interface RoleRepository  extends JpaRepository<AppRoles, Long>{
	 public AppRoles findByRoleName(String role);
}
