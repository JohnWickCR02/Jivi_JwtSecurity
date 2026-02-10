package jwtSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwtSecurity.entity.User;



@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
