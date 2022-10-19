package it.dantonio.bankingapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
public class MoneyTransferResponse implements Serializable {

  private String moneyTransferId;
  private String status;
  private String direction;
  private Creditor creditor;
  private Debtor debtor;
  private String cro;
  private String uri;
  private String trn;
  private String description;
  private LocalDateTime createdDatetime;
  private LocalDateTime accountedDatetime;
  private LocalDate debtorValueDate;
  private LocalDate creditorValueDate;
  private Amount amount;
  private Boolean isUrgent;
  private Boolean isInstant;
  private String feeType;
  private String feeAccountId;
  private List<Fee> fees;
  private Boolean hasTaxRelief;

}
