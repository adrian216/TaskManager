package com.someonesmarter.todo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository theUserRepository, RoleRepository theRoleRepository, BCryptPasswordEncoder theBCryptPasswordEncoder) {
        userRepository=theUserRepository;
        roleRepository=theRoleRepository;
        bCryptPasswordEncoder=theBCryptPasswordEncoder;
    }

    @Override
    public User findUserByEmail(String name) {
        return userRepository.findByEmail(name);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

}
