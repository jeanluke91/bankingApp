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
public class Amount implements Serializable {

  private BigDecimal debtorAmount;
  private String debtorCurrency;
  private BigDecimal creditorAmount;
  private String creditorCurrency;
  private LocalDate creditorCurrencyDate;
  private Long exchangeRate;

}
