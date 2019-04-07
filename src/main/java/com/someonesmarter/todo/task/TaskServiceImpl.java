package com.someonesmarter.todo.task;

import com.someonesmarter.todo.security.User;
import com.someonesmarter.todo.security.UserService;
import com.someonesmarter.todo.security.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	public TaskServiceImpl(TaskRepository theTaskRepository) {
		taskRepository = theTaskRepository;
	}
	
	@Override
	public Set<Task> findAllByUser() {
		String username = UserUtilities.getLoggedUser();
		User user = userService.findUserByEmail(username);
		return taskRepository.findAllByUser(user);
	}

	@Override
	public boolean belongToUser(Task task) {
		String username = UserUtilities.getLoggedUser();
		User user = userService.findUserByEmail(username);
		Set<Task> tasks = taskRepository.findAllByUser(user);
		if (tasks.contains(task))
			return true;
		return false;
	}

	@Override
	public Task findById(int theId) {
		Optional<Task> result = taskRepository.findById(theId);
		
		Task theTask = null;
		
		if (result.isPresent()) {
			theTask = result.get();
		}
		else {
			throw new RuntimeException("Did not find task id - " + theId);
		}
		
		return theTask;
	}

	@Override
	public void save(Task theTask) {
		taskRepository.save(theTask);
	}

	@Override
	public void deleteById(int theId) {
		taskRepository.deleteById(theId);
	}

}






