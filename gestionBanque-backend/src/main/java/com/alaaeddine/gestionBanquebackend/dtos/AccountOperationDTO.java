package com.alaaeddine.gestionBanquebackend.dtos;

import com.alaaeddine.gestionBanquebackend.entities.BankAccount;
import com.alaaeddine.gestionBanquebackend.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}
