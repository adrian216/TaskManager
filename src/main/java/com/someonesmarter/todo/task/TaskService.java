package com.someonesmarter.todo.task;


import java.util.Set;

public interface TaskService {

	 Task findById(int theId);
	 void save(Task theTask);
	 void deleteById(int theId);
	 Set<Task> findAllByUser();
	 boolean belongToUser(Task task);

	
}
