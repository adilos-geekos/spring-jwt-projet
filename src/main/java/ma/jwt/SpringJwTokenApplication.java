package ma.jwt;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.jwt.dao.TaskRepository;
import ma.jwt.entities.AppRoles;
import ma.jwt.entities.AppUSer;
import ma.jwt.entities.Task;
import ma.jwt.service.AccountService;

@SpringBootApplication
public class SpringJwTokenApplication implements CommandLineRunner {
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private AccountService accountService;
	public static void main(String[] args) {
		SpringApplication.run(SpringJwTokenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		accountService.saveUser(new AppUSer("adil","adil",null));
		accountService.saveUser(new AppUSer("maroc","maroc",null));
		accountService.saveRole(new AppRoles("ADMIN"));
		accountService.saveRole(new AppRoles("USER"));
		accountService.addRoleToUser("adil","ADMIN");
		accountService.addRoleToUser("maroc","USER");
		Stream.of("T1","T2","T3").forEach(t->{ taskRepository.save(new Task(t) ); } );
		taskRepository.findAll().forEach(t->{System.out.println(t.getTaskName());});
	}
	
	@Bean
	public BCryptPasswordEncoder getBcrypt() {
		return new BCryptPasswordEncoder();
	}
}
