package com.oose.jobportal.repositories;

import com.oose.jobportal.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
	User findByGmail(String email);
}
