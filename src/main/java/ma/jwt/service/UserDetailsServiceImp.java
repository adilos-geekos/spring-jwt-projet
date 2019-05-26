package ma.jwt.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.jwt.entities.AppUSer;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	private AccountService accountService;
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		AppUSer user = accountService.findUserByUSername(arg0);
		if (user == null) throw new UsernameNotFoundException("");
		Collection<GrantedAuthority> g = new ArrayList<>();
		user.getRoles().forEach(f ->{ g.add(new SimpleGrantedAuthority(f.getRoleName())); });
		return new User(user.getUsename(),user.getPassword(),g);
	}

}
