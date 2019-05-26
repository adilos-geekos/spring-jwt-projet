package ma.jwt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.jwt.entities.AppUSer;
import ma.jwt.service.AccountService;
@RestController
public class AccountRestController {
	
	@Autowired
	private AccountService accountServ;
	@PostMapping("/register")
	public AppUSer register(@RequestBody RegisterForm u) {
		if(!u.getPassword().equals(u.getRepassword()))
			throw new RuntimeException("password not identical");
		AppUSer ap = accountServ.findUserByUSername(u.getUsername());
		if(ap!=null)
			throw new RuntimeException("user already exists");
		AppUSer a = new AppUSer();
		a.setUsename(u.getUsername());
		a.setPassword(u.getPassword());
		return accountServ.saveUser(a);
	}
}
