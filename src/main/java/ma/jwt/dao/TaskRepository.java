package ma.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.jwt.entities.Task;


public interface TaskRepository extends JpaRepository<Task,Long> {

}
