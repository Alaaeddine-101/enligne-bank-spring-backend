package com.alaaeddine.gestionBanquebackend.repositories;

import com.alaaeddine.gestionBanquebackend.entities.AccountOperation;
import com.alaaeddine.gestionBanquebackend.entities.BankAccount;
import com.alaaeddine.gestionBanquebackend.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepositoriy extends JpaRepository<BankAccount, String> {

}
