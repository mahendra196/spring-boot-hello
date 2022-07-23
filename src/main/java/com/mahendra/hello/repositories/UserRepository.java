package com.mahendra.hello.repositories;

import com.mahendra.hello.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
