package com.alaaeddine.gestionBanquebackend.repositories;

import com.alaaeddine.gestionBanquebackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositoriy extends JpaRepository<Customer, Long> {
}
