package com.alaaeddine.gestionBanquebackend;

import com.alaaeddine.gestionBanquebackend.dtos.BankAccountDTO;
import com.alaaeddine.gestionBanquebackend.dtos.CurrentBankAccountDTO;
import com.alaaeddine.gestionBanquebackend.dtos.CustomerDTO;
import com.alaaeddine.gestionBanquebackend.dtos.SavingBankAccountDTO;
import com.alaaeddine.gestionBanquebackend.entities.*;
import com.alaaeddine.gestionBanquebackend.enums.AccountStatus;
import com.alaaeddine.gestionBanquebackend.enums.OperationType;
import com.alaaeddine.gestionBanquebackend.exceptions.BalanceNotSufficientException;
import com.alaaeddine.gestionBanquebackend.exceptions.BankAccountNotFoundException;
import com.alaaeddine.gestionBanquebackend.exceptions.CustomerNotFoundException;
import com.alaaeddine.gestionBanquebackend.repositories.AccountOperationRepositoriy;
import com.alaaeddine.gestionBanquebackend.repositories.BankAccountRepositoriy;
import com.alaaeddine.gestionBanquebackend.repositories.CustomerRepositoriy;
import com.alaaeddine.gestionBanquebackend.services.BankAccountService;
import com.alaaeddine.gestionBanquebackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionBanqueBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBanqueBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
		return args -> {
			Stream.of("Jihad", "Aymen", "Ziyad").forEach(name ->{
				CustomerDTO customer = new CustomerDTO();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				bankAccountService.saveCustomer(customer);
			});
			bankAccountService.listCustomers().forEach(customer -> {
				try {
					bankAccountService.saveCurrentBankAccount(Math.random()*9000, 9000, customer.getId());
					bankAccountService.saveSavingBankAccount(Math.random()*12000, 5.5, customer.getId());

				}catch (CustomerNotFoundException e){
					e.printStackTrace();
				}
			});
			List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
			for(BankAccountDTO bankAccount:bankAccounts) {
				for (int i = 0; i < 10; i++) {
					String accountId;
					if(bankAccount instanceof SavingBankAccountDTO){
						accountId=((SavingBankAccountDTO) bankAccount).getId();
					} else{
						accountId=((CurrentBankAccountDTO) bankAccount).getId();
					}
					bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
					bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
				}
			};
		};
	}
//	@Bean
	CommandLineRunner commandLineRunner(CustomerRepositoriy customerRepositoriy, BankAccountRepositoriy bankAccountRepositoriy, AccountOperationRepositoriy accountOperationRepositoriy){
		return args -> {
			Stream.of("Alaa", "Abdo", "Yazid", "Noha").forEach(name ->{
					Customer customer = new Customer();
					customer.setName(name);
					customer.setEmail(name+"@gmail.com");
					customerRepositoriy.save(customer);
			});
			customerRepositoriy.findAll().forEach(customer -> {
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*8000);
				currentAccount.setCreatedDate(new Date());
				currentAccount.setStatus(AccountStatus.ACTIVATED);
				currentAccount.setCustomer(customer);
				currentAccount.setOverDraft(8000);
				bankAccountRepositoriy.save(currentAccount);

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*8000);
				savingAccount.setCreatedDate(new Date());
				savingAccount.setStatus(AccountStatus.ACTIVATED);
				savingAccount.setCustomer(customer);
				savingAccount.setInterestRate(5.5);
				bankAccountRepositoriy.save(savingAccount);
			});
			bankAccountRepositoriy.findAll().forEach(bankAccount -> {
				for (int i=0; i<7; i++){
					AccountOperation accountOperation = new AccountOperation();
					accountOperation.setBankAccount(bankAccount);
					accountOperation.setOperationDate(new Date());
					accountOperation.setType(Math.random()>0.5? OperationType.CREDIT:OperationType.DEBIT);
					accountOperation.setAmount(Math.random()*5000);
					accountOperationRepositoriy.save(accountOperation);
				}
			});
		};
	}

}
