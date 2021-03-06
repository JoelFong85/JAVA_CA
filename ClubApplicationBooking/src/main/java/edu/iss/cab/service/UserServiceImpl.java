package edu.iss.cab.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iss.cab.model.User;
import edu.iss.cab.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserRepository userRepository;

	@Override
	@Transactional
	public ArrayList<User> findAllUsers() {
		ArrayList<User> ul = (ArrayList<User>) userRepository.findAll();
		return ul;
	}

	@Override
	@Transactional
	public User findUser(Integer userId) {
		return userRepository.findOne(userId);
	}

	@Override
	@Transactional
	public User createUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Transactional
	public User changeUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Transactional
	public void removeUser(User user) {
		userRepository.delete(user);
	}

	@Transactional
	public User authenticate(String uname, String pwd) {
		User u = userRepository.findUserByNamePwd(uname, pwd);
		return u;
	}

	@Transactional
	public ArrayList<User> findAllMember() {
		ArrayList<User> ul = (ArrayList<User>) userRepository.findMemberByRole();
		return ul;
	}

	@Transactional
	public ArrayList<User> findAllAdmin() {
		ArrayList<User> ul = (ArrayList<User>) userRepository.findAdminByRole();
		return ul;
	}

	@Transactional
	public User findUserByName(String userName) {
		return userRepository.findUserWithName(userName);
	}
}
