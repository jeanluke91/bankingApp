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
public class AccountBalance implements Serializable {

    private LocalDate date;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private String currency;

}
