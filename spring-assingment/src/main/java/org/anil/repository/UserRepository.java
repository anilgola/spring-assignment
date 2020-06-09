package org.anil.repository;

import java.util.Optional;

import org.anil.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByUsername(String username);
	    
	 @Query("select u.id from User u where u.username = :username")
	 Long  getUserIdByUsername(@Param("username") String username);
}
