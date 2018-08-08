package edu.iss.cab.service;

import java.util.ArrayList;

import edu.iss.cab.model.User;

public interface UserService {

	ArrayList<User> findAllUsers();

	User findUser(Integer userId);

	User findUserByName(String userName);

	User createUser(User user);

	User changeUser(User user);

	void removeUser(User user);

	User authenticate(String uname, String pwd);

	ArrayList<User> findAllMember();

	ArrayList<User> findAllAdmin();
}