package ma.jwt.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// @Data @NoArgsConstructor @AllArgsConstructor 
public class Task implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private String taskName;
	public Task() {
	}
	public Task(String taskName) {
		super();
		this.taskName = taskName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	
}
