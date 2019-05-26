package ma.jwt.web;

import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ma.jwt.dao.TaskRepository;
import ma.jwt.dao.UserRepository;
import ma.jwt.entities.AppUSer;
import ma.jwt.entities.Task;
import ma.jwt.security.SecurityConstants;

@RestController
public class TaskRestController {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/tasks")
	public List<Task> lisTasks(){
		return taskRepository.findAll();
	}
	
	@GetMapping("/users")
	public List<AppUSer> listUSers(){
		return userRepository.findAll();
	}
	
	@PostMapping("/tasks")
	public Task save(@RequestBody Task t) {
		return taskRepository.save(t);
	}
	@GetMapping("/")
	@ResponseBody
	public String listUSr(HttpServletResponse r,HttpServletRequest request){
		System.out.println("dddddddddddddddd "+r.getHeader("authorization"));
		return r.getHeader("authorization");
	
	}

}
