package edu.iss.cab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.iss.cab.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.userName=:un AND u.password=:pwd")
	User findUserByNamePwd(@Param("un") String uname, @Param("pwd") String pwd);

	@Query("SELECT u FROM User u WHERE u.userName=:un")
	User findUserWithName(@Param("un") String uname);

	@Query(value = "SELECT * FROM cab.user u WHERE u.role='member'", nativeQuery = true)
	List<User> findMemberByRole();

	@Query(value = "SELECT * FROM cab.user u WHERE u.role='admin'", nativeQuery = true)
	List<User> findAdminByRole();
}
