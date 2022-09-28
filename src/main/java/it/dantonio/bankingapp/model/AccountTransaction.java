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
