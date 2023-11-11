package com.tiptop.users.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tiptop.users.entities.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	@Query(value = "SELECT * FROM User ORDER BY RAND() LIMIT 1 Where ticketNumber NOT NULL", nativeQuery = true)
	User findRandomUser();
}