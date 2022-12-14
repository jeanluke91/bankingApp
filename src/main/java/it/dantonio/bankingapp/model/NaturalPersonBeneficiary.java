package it.dantonio.bankingapp.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
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
public class NaturalPersonBeneficiary implements Serializable {

  @NotNull(message = "NaturalPersonBeneficiary fiscalcode1 is mandatory")
  private String fiscalCode1;
  private String fiscalCode2;
  private String fiscalCode3;
  private String fiscalCode4;
  private String fiscalCode5;

}
