package com.someonesmarter.todo.task;

import com.someonesmarter.todo.security.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String description;

	private boolean status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm ")
	private Date deadline;

	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="user_id")
	private User user;


//	@Column(name="reminder")
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm ")
//	private Date reminder;
	

	public Task() {
		
	}
	
	public Task(int id, String title, String description, Date deadline) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.deadline = deadline;
	}


	public Task(String title, String description, Date date) {
		this.title = title;
		this.description = description;
		this.deadline = date;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Task{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", deadline=" + deadline +
				'}';
	}

}











