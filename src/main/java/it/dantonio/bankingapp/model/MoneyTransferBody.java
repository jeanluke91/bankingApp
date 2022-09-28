package it.dantonio.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class MoneyTransferBody implements Serializable {

  @NotNull(message = "Creditor is mandatory")
  private Creditor creditor;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate executionDate;
  private String uri;
  @NotNull(message = "description is mandatory")
  @Size(max = 140)
  private String description;
  @NotNull(message = "amount is mandatory")
  private BigDecimal amount;
  @NotNull(message = "currency is mandatory")
  private String currency;
  private Boolean isUrgent;
  private Boolean isInstant;
  private String feeType;
  private String feeAccountId;
  private TaxRelief taxRelief;
}
