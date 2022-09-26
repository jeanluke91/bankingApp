package it.dantonio.bankingapp.model;


import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
