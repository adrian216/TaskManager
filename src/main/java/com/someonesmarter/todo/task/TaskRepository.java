package com.someonesmarter.todo.task;

import com.someonesmarter.todo.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	Set<Task> findAllByOrderByTitleAsc();
	Set<Task> findAllByOrderByDeadlineAsc();
	Set<Task> findAllByUser(User user);
}
