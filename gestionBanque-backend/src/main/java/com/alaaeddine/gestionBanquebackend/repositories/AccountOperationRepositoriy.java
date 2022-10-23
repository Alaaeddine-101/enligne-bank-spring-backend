package com.alaaeddine.gestionBanquebackend.repositories;

import com.alaaeddine.gestionBanquebackend.entities.AccountOperation;
import com.alaaeddine.gestionBanquebackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepositoriy extends JpaRepository<AccountOperation, Long> {
}
