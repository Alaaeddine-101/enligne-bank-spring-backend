package com.alaaeddine.gestionBanquebackend.mappers;

import com.alaaeddine.gestionBanquebackend.dtos.AccountOperationDTO;
import com.alaaeddine.gestionBanquebackend.dtos.CurrentBankAccountDTO;
import com.alaaeddine.gestionBanquebackend.dtos.CustomerDTO;
import com.alaaeddine.gestionBanquebackend.dtos.SavingBankAccountDTO;
import com.alaaeddine.gestionBanquebackend.entities.AccountOperation;
import com.alaaeddine.gestionBanquebackend.entities.CurrentAccount;
import com.alaaeddine.gestionBanquebackend.entities.Customer;
import com.alaaeddine.gestionBanquebackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
//MapStruct
@Service
public class BankAccountMappersImpl {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO =new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
//        customerDTO.setId(customer.getId());
//        customerDTO.setName(customer.getName());
//        customerDTO.setEmail(customer.getEmail());
        return  customerDTO;
    }
    public Customer fromCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return  customer;
    }

    public SavingBankAccountDTO fromSavingAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomer(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        savingAccount.setCustomer(fromCustomer(savingBankAccountDTO.getCustomer()));
        return savingAccount;
    }

    public CurrentBankAccountDTO fromCurrentAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        currentBankAccountDTO.setCustomer(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }

    public CurrentAccount fromCurrentAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties( currentBankAccountDTO, currentAccount);
        currentAccount.setCustomer(fromCustomer(currentBankAccountDTO.getCustomer()));
        return currentAccount;
    }
    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        return accountOperationDTO;
    }
}
