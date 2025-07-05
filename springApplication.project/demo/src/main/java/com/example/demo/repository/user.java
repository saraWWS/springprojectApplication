package com.contactsaver.demo.repository;

import com.contactsaver.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // You can define custom query methods here, for example:
    // Optional<User> findByEmail(String email);
}
