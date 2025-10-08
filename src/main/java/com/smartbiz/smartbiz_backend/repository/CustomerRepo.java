package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);
    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:query% OR c.email LIKE %:query% OR c.phone LIKE %:query%")
    List<Customer> searchCustomers(@Param("query") String query);
}
