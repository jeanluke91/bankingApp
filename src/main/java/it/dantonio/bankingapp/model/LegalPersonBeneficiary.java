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
public class LegalPersonBeneficiary implements Serializable {

  @NotNull(message = "LegalPersonBeneficiary fiscalcode is mandatory")
  private String fiscalCode;
  private String legalRepresentativeFiscalCode;

}
