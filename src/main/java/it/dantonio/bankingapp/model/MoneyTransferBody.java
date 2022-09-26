package it.dantonio.bankingapp.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Accessors(chain = true)
public class MoneyTransferBody implements Serializable {

    private Creditor creditor;
    private LocalDate executionDate;
    private String uri;
    private String description;
    private BigDecimal amount;
    private String currency;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private TaxRelief taxRelief;
}
