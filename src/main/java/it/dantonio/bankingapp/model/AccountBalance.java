package it.dantonio.bankingapp.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
