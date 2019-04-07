package com.someonesmarter.todo.security;


public interface UserService {
     User findUserByEmail(String name);
     void saveUser(User user);
}
