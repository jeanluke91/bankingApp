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
public class TaxRelief implements Serializable {

  private String taxReliefId;
  @NotNull(message = "isCondoUpgrade is mandatory")
  private Boolean isCondoUpgrade;
  @NotNull(message = "creditorFiscalCode is mandatory")
  private String creditorFiscalCode;
  @NotNull(message = "beneficiaryType is mandatory")
  private String beneficiaryType;
  private NaturalPersonBeneficiary naturalPersonBeneficiary;
  private LegalPersonBeneficiary legalPersonBeneficiary;

}
