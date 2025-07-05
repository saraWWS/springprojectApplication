package com.contactsaver.demo.repository;

import com.contactsaver.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    // Add custom query methods if needed, for example:
    // List<Contact> findByFirstName(String firstName);
}