package ma.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.jwt.dao.RoleRepository;
import ma.jwt.dao.UserRepository;
import ma.jwt.entities.AppRoles;
import ma.jwt.entities.AppUSer;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private BCryptPasswordEncoder b;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public AppUSer saveUser(AppUSer u) {
		String hash = b.encode(u.getPassword());
		u.setPassword(hash);
		return userRepository.save(u);
	}

	@Override
	public AppRoles saveRole(AppRoles r) {
		return roleRepository.save(r);
	}

	@Override
	public void addRoleToUser(String u, String r) {
		AppUSer user = userRepository.findByUsename(u); 
		AppRoles role = roleRepository.findByRoleName(r);
		user.getRoles().add(role);
	}

	@Override
	public AppUSer findUserByUSername(String u) {
		return userRepository.findByUsename(u);
	}

}
