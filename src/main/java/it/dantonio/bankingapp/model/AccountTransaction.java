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
public class AccountTransaction implements Serializable {

    private String transactionId;
    private String operationId;
    private LocalDate accountingDate;
    private LocalDate valueDate;
    private Enumeration type;
    private BigDecimal amount;
    private String currency;
    private String description;

}
