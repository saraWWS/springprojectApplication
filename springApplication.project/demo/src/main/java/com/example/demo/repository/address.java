package com.contactsaver.demo.repository;

import com.contactsaver.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    // You can add custom query methods here if needed
    // Example: List<Address> findByCity(String city);
}