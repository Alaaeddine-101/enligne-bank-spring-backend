package com.alaaeddine.gestionBanquebackend.services;

import com.alaaeddine.gestionBanquebackend.dtos.*;
import com.alaaeddine.gestionBanquebackend.entities.BankAccount;
import com.alaaeddine.gestionBanquebackend.entities.CurrentAccount;
import com.alaaeddine.gestionBanquebackend.entities.Customer;
import com.alaaeddine.gestionBanquebackend.entities.SavingAccount;
import com.alaaeddine.gestionBanquebackend.exceptions.BalanceNotSufficientException;
import com.alaaeddine.gestionBanquebackend.exceptions.BankAccountNotFoundException;
import com.alaaeddine.gestionBanquebackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customer);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long id) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerID);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDTO> searchCustomers(String keyword);
}
