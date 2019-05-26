package ma.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.jwt.entities.AppUSer;

public interface UserRepository extends JpaRepository<AppUSer, Long>{
	public AppUSer findByUsename(String username);
}
