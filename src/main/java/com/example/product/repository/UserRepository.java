package com.example.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product.domain.User;

/**
 * Repository for User Entity
 * 
 * @author SAMYAK JAIN
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * Fetch user by Username  
	 * 
	 * @param username
	 * @return
	 */
	Optional<User> findByUsername(String username);
}
