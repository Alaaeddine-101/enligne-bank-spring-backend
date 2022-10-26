package com.alaaeddine.gestionBanquebackend.dtos;
import com.alaaeddine.gestionBanquebackend.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data  @NoArgsConstructor @AllArgsConstructor
public class CurrentBankAccountDTO extends BankAccountDTO{
    private String id;
    private double balance;
    private Date createdDate;
    private AccountStatus status;
    private CustomerDTO customer;
    private double overtDraft;
}
